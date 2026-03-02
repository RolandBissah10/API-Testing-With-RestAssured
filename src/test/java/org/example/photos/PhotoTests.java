package org.example.photos;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("REST API Test Suite")
@Feature("Photos Management")
public class PhotoTests extends BaseTest {

    @ParameterizedTest(name = "GET photo with ID={0}")
    @Story("GET Photo by ID")
    @Description("Verify fetching a photo by ID.")
    @MethodSource("org.example.photos.PhotoData#getPhotoIds")
    public void testGetPhoto(int id) {
        given()
                .when()
                .get("/photos/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("albumId", notNullValue())
                .body("title", notNullValue())
                .body("url", notNullValue())
                .body("thumbnailUrl", notNullValue())
                .body(matchesJsonSchemaInClasspath("photo-schema.json"));
    }

    @ParameterizedTest(name = "POST create photo: title={0}, albumId={3}")
    @Story("POST Create new Photo")
    @Description("Verify creating a photo.")
    @MethodSource("org.example.photos.PhotoData#getNewPhotos")
    public void testCreatePhoto(String title, String url, String thumbnailUrl, int albumId) {
        given()
                .body(PhotoData.buildNewPhoto(title, url, thumbnailUrl, albumId))
                .when()
                .post("/photos")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo(title))
                .body("url", equalTo(url))
                .body("thumbnailUrl", equalTo(thumbnailUrl))
                .body("albumId", equalTo(albumId));
    }

    @ParameterizedTest(name = "PUT update photo ID={0}")
    @Story("PUT Update Photo")
    @Description("Verify updating a photo.")
    @MethodSource("org.example.photos.PhotoData#getUpdatedPhotos")
    public void testUpdatePhoto(int id, String title, String url, String thumbnailUrl, int albumId) {
        given()
                .body(PhotoData.buildUpdatedPhoto(id, title, url, thumbnailUrl, albumId))
                .when()
                .put("/photos/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("title", equalTo(title))
                .body("url", equalTo(url))
                .body("thumbnailUrl", equalTo(thumbnailUrl))
                .body("albumId", equalTo(albumId));
    }

    @ParameterizedTest(name = "DELETE photo with ID={0}")
    @Story("DELETE Remove Photo")
    @Description("Verify deleting a photo.")
    @MethodSource("org.example.photos.PhotoData#getPhotoIds")
    public void testDeletePhoto(int id) {
        given()
                .when()
                .delete("/photos/" + id)
                .then()
                .statusCode(200);
    }
}

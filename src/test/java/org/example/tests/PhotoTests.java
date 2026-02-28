package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.base.BaseTest;
import org.example.data.PhotoData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("REST API Test Suite")
@Feature("Photos Management")
public class PhotoTests extends BaseTest {

    @Test
    @Story("GET Photo by ID")
    @Description("Verify fetching a photo by ID.")
    public void testGetPhoto() {
        given()
                .when()
                .get("/photos/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("albumId", equalTo(1))
                .body("title", notNullValue())
                .body("url", notNullValue())
                .body("thumbnailUrl", notNullValue())
                .body(matchesJsonSchemaInClasspath("photo-schema.json"));
    }

    @Test
    @Story("POST Create new Photo")
    @Description("Verify creating a photo.")
    public void testCreatePhoto() {
        given()
                .body(PhotoData.getNewPhoto())
                .when()
                .post("/photos")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo("Sample Photo Title"))
                .body("url", equalTo("https://via.placeholder.com/600/92c952"))
                .body("thumbnailUrl", equalTo("https://via.placeholder.com/150/92c952"))
                .body("albumId", equalTo(1));
    }

    @Test
    @Story("PUT Update Photo")
    @Description("Verify updating a photo.")
    public void testUpdatePhoto() {
        given()
                .body(PhotoData.getUpdatedPhoto(1))
                .when()
                .put("/photos/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Updated Photo Title"))
                .body("url", equalTo("https://via.placeholder.com/600/92c952"))
                .body("thumbnailUrl", equalTo("https://via.placeholder.com/150/92c952"))
                .body("albumId", equalTo(1));
    }

    @Test
    @Story("DELETE Remove Photo")
    @Description("Verify deleting a photo.")
    public void testDeletePhoto() {
        given()
                .when()
                .delete("/photos/1")
                .then()
                .statusCode(200);
    }
}

package org.example.albums;

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
@Feature("Albums Management")
public class AlbumTests extends BaseTest {

    @ParameterizedTest(name = "GET album with ID={0}")
    @Story("GET Album by ID")
    @Description("Verify fetching an album by ID.")
    @MethodSource("org.example.albums.AlbumData#getAlbumIds")
    public void testGetAlbum(int id) {
        given()
                .when()
                .get("/albums/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body(matchesJsonSchemaInClasspath("album-schema.json"));
    }

    @ParameterizedTest(name = "POST create album: title={0}, userId={1}")
    @Story("POST Create new Album")
    @Description("Verify creating an album.")
    @MethodSource("org.example.albums.AlbumData#getNewAlbums")
    public void testCreateAlbum(String title, int userId) {
        given()
                .body(AlbumData.buildNewAlbum(title, userId))
                .when()
                .post("/albums")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo(title))
                .body("userId", equalTo(userId));
    }

    @ParameterizedTest(name = "PUT update album ID={0}")
    @Story("PUT Update Album")
    @Description("Verify updating an album.")
    @MethodSource("org.example.albums.AlbumData#getUpdatedAlbums")
    public void testUpdateAlbum(int id, String title, int userId) {
        given()
                .body(AlbumData.buildUpdatedAlbum(id, title, userId))
                .when()
                .put("/albums/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("title", equalTo(title))
                .body("userId", equalTo(userId));
    }

    @ParameterizedTest(name = "DELETE album with ID={0}")
    @Story("DELETE Remove Album")
    @Description("Verify deleting an album.")
    @MethodSource("org.example.albums.AlbumData#getAlbumIds")
    public void testDeleteAlbum(int id) {
        given()
                .when()
                .delete("/albums/" + id)
                .then()
                .statusCode(200);
    }
}

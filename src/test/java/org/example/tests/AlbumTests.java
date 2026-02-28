package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.base.BaseTest;
import org.example.data.AlbumData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("REST API Test Suite")
@Feature("Albums Management")
public class AlbumTests extends BaseTest {

    @Test
    @Story("GET Album by ID")
    @Description("Verify fetching an album by ID.")
    public void testGetAlbum() {
        given()
                .when()
                .get("/albums/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", notNullValue())
                .body(matchesJsonSchemaInClasspath("album-schema.json"));
    }

    @Test
    @Story("POST Create new Album")
    @Description("Verify creating an album.")
    public void testCreateAlbum() {
        given()
                .body(AlbumData.getNewAlbum())
                .when()
                .post("/albums")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo("Sample Album Title"))
                .body("userId", equalTo(1));
    }

    @Test
    @Story("PUT Update Album")
    @Description("Verify updating an album.")
    public void testUpdateAlbum() {
        given()
                .body(AlbumData.getUpdatedAlbum(1))
                .when()
                .put("/albums/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Updated Album Title"))
                .body("userId", equalTo(1));
    }

    @Test
    @Story("DELETE Remove Album")
    @Description("Verify deleting an album.")
    public void testDeleteAlbum() {
        given()
                .when()
                .delete("/albums/1")
                .then()
                .statusCode(200);
    }
}

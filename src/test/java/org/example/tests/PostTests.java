package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.base.BaseTest;
import org.example.data.PostData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("REST API Test Suite")
@Feature("Posts Management")
public class PostTests extends BaseTest {

    @Test
    @Story("GET Post by ID")
    @Description("Verify fetching a post by ID.")
    public void testGetPost() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", notNullValue())
                .body("body", notNullValue())
                .body(matchesJsonSchemaInClasspath("post-schema.json"));
    }

    @Test
    @Story("POST Create new Post")
    @Description("Verify creating a post.")
    public void testCreatePost() {
        given()
                .body(PostData.getNewPost())
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo("Sample Post Title"))
                .body("body", equalTo("Sample Post Body"))
                .body("userId", equalTo(1));
    }

    @Test
    @Story("PUT Update Post")
    @Description("Verify updating a post.")
    public void testUpdatePost() {
        given()
                .body(PostData.getUpdatedPost(1))
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Updated Post Title"))
                .body("body", equalTo("Updated Post Body"))
                .body("userId", equalTo(1));
    }

    @Test
    @Story("DELETE Remove Post")
    @Description("Verify deleting a post.")
    public void testDeletePost() {
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);
    }
}

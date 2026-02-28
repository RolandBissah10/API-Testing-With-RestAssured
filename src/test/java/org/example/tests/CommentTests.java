package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.base.BaseTest;
import org.example.data.CommentData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("REST API Test Suite")
@Feature("Comments Management")
public class CommentTests extends BaseTest {

    @Test
    @Story("GET Comment by ID")
    @Description("Verify fetching a comment by ID.")
    public void testGetComment() {
        given()
                .when()
                .get("/comments/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("postId", equalTo(1))
                .body("name", notNullValue())
                .body("email", notNullValue())
                .body("body", notNullValue())
                .body(matchesJsonSchemaInClasspath("comment-schema.json"));
    }

    @Test
    @Story("POST Create new Comment")
    @Description("Verify creating a comment.")
    public void testCreateComment() {
        given()
                .body(CommentData.getNewComment())
                .when()
                .post("/comments")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Sample Comment Name"))
                .body("email", equalTo("test@example.com"))
                .body("body", equalTo("Sample Comment Body"))
                .body("postId", equalTo(1));
    }

    @Test
    @Story("PUT Update Comment")
    @Description("Verify updating a comment.")
    public void testUpdateComment() {
        given()
                .body(CommentData.getUpdatedComment(1))
                .when()
                .put("/comments/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Updated Comment Name"))
                .body("email", equalTo("updated@example.com"))
                .body("body", equalTo("Updated Comment Body"))
                .body("postId", equalTo(1));
    }

    @Test
    @Story("DELETE Remove Comment")
    @Description("Verify deleting a comment.")
    public void testDeleteComment() {
        given()
                .when()
                .delete("/comments/1")
                .then()
                .statusCode(200);
    }
}

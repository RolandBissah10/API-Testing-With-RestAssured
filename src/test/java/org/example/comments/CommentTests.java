package org.example.comments;

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
@Feature("Comments Management")
public class CommentTests extends BaseTest {

    @ParameterizedTest(name = "GET comment with ID={0}")
    @Story("GET Comment by ID")
    @Description("Verify fetching a comment by ID.")
    @MethodSource("org.example.comments.CommentData#getCommentIds")
    public void testGetComment(int id) {
        given()
                .when()
                .get("/comments/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("postId", notNullValue())
                .body("name", notNullValue())
                .body("email", notNullValue())
                .body("body", notNullValue())
                .body(matchesJsonSchemaInClasspath("comment-schema.json"));
    }

    @ParameterizedTest(name = "POST create comment: name={0}, postId={3}")
    @Story("POST Create new Comment")
    @Description("Verify creating a comment.")
    @MethodSource("org.example.comments.CommentData#getNewComments")
    public void testCreateComment(String name, String email, String body, int postId) {
        given()
                .body(CommentData.buildNewComment(name, email, body, postId))
                .when()
                .post("/comments")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo(name))
                .body("email", equalTo(email))
                .body("body", equalTo(body))
                .body("postId", equalTo(postId));
    }

    @ParameterizedTest(name = "PUT update comment ID={0}")
    @Story("PUT Update Comment")
    @Description("Verify updating a comment.")
    @MethodSource("org.example.comments.CommentData#getUpdatedComments")
    public void testUpdateComment(int id, String name, String email, String body, int postId) {
        given()
                .body(CommentData.buildUpdatedComment(id, name, email, body, postId))
                .when()
                .put("/comments/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("email", equalTo(email))
                .body("body", equalTo(body))
                .body("postId", equalTo(postId));
    }

    @ParameterizedTest(name = "DELETE comment with ID={0}")
    @Story("DELETE Remove Comment")
    @Description("Verify deleting a comment.")
    @MethodSource("org.example.comments.CommentData#getCommentIds")
    public void testDeleteComment(int id) {
        given()
                .when()
                .delete("/comments/" + id)
                .then()
                .statusCode(200);
    }
}

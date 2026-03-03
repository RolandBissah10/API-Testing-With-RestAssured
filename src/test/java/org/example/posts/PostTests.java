package org.example.posts;

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
@Feature("Posts Management")
public class PostTests extends BaseTest {

    @ParameterizedTest(name = "GET post with ID={0}")
    @Story("GET Post by ID")
    @Description("Verify fetching a post by ID.")
    @MethodSource("org.example.posts.PostData#getPostIds")
    public void testGetPost(int id) {
        given()
                .when()
                .get(PostEndpoint.getById(id))
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("title", notNullValue())
                .body("body", notNullValue())
                .body(matchesJsonSchemaInClasspath("post-schema.json"));
    }

    @ParameterizedTest(name = "POST create post: title={0}, userId={2}")
    @Story("POST Create new Post")
    @Description("Verify creating a post.")
    @MethodSource("org.example.posts.PostData#getNewPosts")
    public void testCreatePost(String title, String body, int userId) {
        given()
                .body(PostPayload.buildNewPost(title, body, userId))
                .when()
                .post(PostEndpoint.POSTS)
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo(title))
                .body("body", equalTo(body))
                .body("userId", equalTo(userId));
    }

    @ParameterizedTest(name = "PUT update post ID={0}")
    @Story("PUT Update Post")
    @Description("Verify updating a post.")
    @MethodSource("org.example.posts.PostData#getUpdatedPosts")
    public void testUpdatePost(int id, String title, String body, int userId) {
        given()
                .body(PostPayload.buildUpdatedPost(id, title, body, userId))
                .when()
                .put(PostEndpoint.getById(id))
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("title", equalTo(title))
                .body("body", equalTo(body))
                .body("userId", equalTo(userId));
    }

    @ParameterizedTest(name = "DELETE post with ID={0}")
    @Story("DELETE Remove Post")
    @Description("Verify deleting a post.")
    @MethodSource("org.example.posts.PostData#getPostIds")
    public void testDeletePost(int id) {
        given()
                .when()
                .delete(PostEndpoint.getById(id))
                .then()
                .statusCode(200);
    }
}

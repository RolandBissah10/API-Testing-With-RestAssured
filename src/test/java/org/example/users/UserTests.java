package org.example.users;

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
@Feature("User Management")
public class UserTests extends BaseTest {

    @ParameterizedTest(name = "GET user with ID={0}")
    @Story("GET User by ID")
    @Description("Verify that a user can be fetched by their ID and validates the schema.")
    @MethodSource("org.example.users.UserData#getUserIds")
    public void testGetUser(int id) {
        given()
                .when()
                .get("/users/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", notNullValue())
                .body("username", notNullValue())
                .body("email", notNullValue())
                .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }

    @ParameterizedTest(name = "POST create user: name={0}, username={1}")
    @Story("POST Create new User")
    @Description("Verify that a completely new user can be created.")
    @MethodSource("org.example.users.UserData#getNewUsers")
    public void testCreateUser(String name, String username, String email) {
        given()
                .body(UserData.buildNewUser(name, username, email))
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo(name))
                .body("username", equalTo(username))
                .body("email", equalTo(email));
    }

    @ParameterizedTest(name = "PUT update user ID={0}")
    @Story("PUT Update User")
    @Description("Verify that an existing user's details can be updated.")
    @MethodSource("org.example.users.UserData#getUpdatedUsers")
    public void testUpdateUser(int id, String name, String username, String email) {
        given()
                .body(UserData.buildUpdatedUser(id, name, username, email))
                .when()
                .put("/users/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("username", equalTo(username))
                .body("email", equalTo(email));
    }

    @ParameterizedTest(name = "DELETE user with ID={0}")
    @Story("DELETE Remove User")
    @Description("Verify that a user can be deleted successfully.")
    @MethodSource("org.example.users.UserData#getUserIds")
    public void testDeleteUser(int id) {
        given()
                .when()
                .delete("/users/" + id)
                .then()
                .statusCode(200);
    }
}

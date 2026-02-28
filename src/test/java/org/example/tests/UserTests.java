package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.base.BaseTest;
import org.example.data.UserData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("REST API Test Suite")
@Feature("User Management")
public class UserTests extends BaseTest {

    @Test
    @Story("GET User by ID")
    @Description("Verify that a user can be fetched by their ID and validates the schema.")
    public void testGetUser() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue())
                .body("username", notNullValue())
                .body("email", notNullValue())
                .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }

    @Test
    @Story("POST Create new User")
    @Description("Verify that a completely new user can be created.")
    public void testCreateUser() {
        given()
                .body(UserData.getNewUser())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("John Doe"))
                .body("username", equalTo("johndoe"))
                .body("email", equalTo("johndoe@example.com"));
    }

    @Test
    @Story("PUT Update User")
    @Description("Verify that an existing user's details can be updated.")
    public void testUpdateUser() {
        given()
                .body(UserData.getUpdatedUser(1))
                .when()
                .put("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("John Smith Updated"))
                .body("username", equalTo("jsmith"))
                .body("email", equalTo("jsmith@example.com"));
    }

    @Test
    @Story("DELETE Remove User")
    @Description("Verify that a user can be deleted successfully.")
    public void testDeleteUser() {
        given()
                .when()
                .delete("/users/1")
                .then()
                .statusCode(200);
    }
}

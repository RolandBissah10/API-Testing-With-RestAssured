package org.example.todos;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.base.BaseTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("REST API Test Suite")
@Feature("Todos Management")
public class TodoTests extends BaseTest {

    @Test
    @Story("GET Todo by ID")
    @Description("Verify fetching a todo by ID.")
    public void testGetTodo() {
        given()
                .when()
                .get("/todos/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", notNullValue())
                .body("completed", notNullValue())
                .body(matchesJsonSchemaInClasspath("todo-schema.json"));
    }

    @Test
    @Story("POST Create new Todo")
    @Description("Verify creating a todo.")
    public void testCreateTodo() {
        given()
                .body(TodoData.getNewTodo())
                .when()
                .post("/todos")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo("Sample Todo"))
                .body("completed", equalTo(false))
                .body("userId", equalTo(1));
    }

    @Test
    @Story("PUT Update Todo")
    @Description("Verify updating a todo.")
    public void testUpdateTodo() {
        given()
                .body(TodoData.getUpdatedTodo(1))
                .when()
                .put("/todos/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Updated Todo"))
                .body("completed", equalTo(true))
                .body("userId", equalTo(1));
    }

    @Test
    @Story("DELETE Remove Todo")
    @Description("Verify deleting a todo.")
    public void testDeleteTodo() {
        given()
                .when()
                .delete("/todos/1")
                .then()
                .statusCode(200);
    }
}

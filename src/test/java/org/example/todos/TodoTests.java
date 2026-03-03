package org.example.todos;

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
@Feature("Todos Management")
public class TodoTests extends BaseTest {

    @ParameterizedTest(name = "GET todo with ID={0}")
    @Story("GET Todo by ID")
    @Description("Verify fetching a todo by ID.")
    @MethodSource("org.example.todos.TodoData#getTodoIds")
    public void testGetTodo(int id) {
        given()
                .when()
                .get(TodoEndpoint.getById(id))
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("completed", notNullValue())
                .body(matchesJsonSchemaInClasspath("todo-schema.json"));
    }

    @ParameterizedTest(name = "POST create todo: title={0}, userId={2}")
    @Story("POST Create new Todo")
    @Description("Verify creating a todo.")
    @MethodSource("org.example.todos.TodoData#getNewTodos")
    public void testCreateTodo(String title, boolean completed, int userId) {
        given()
                .body(TodoPayload.buildNewTodo(title, completed, userId))
                .when()
                .post(TodoEndpoint.TODOS)
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo(title))
                .body("completed", equalTo(completed))
                .body("userId", equalTo(userId));
    }

    @ParameterizedTest(name = "PUT update todo ID={0}")
    @Story("PUT Update Todo")
    @Description("Verify updating a todo.")
    @MethodSource("org.example.todos.TodoData#getUpdatedTodos")
    public void testUpdateTodo(int id, String title, boolean completed, int userId) {
        given()
                .body(TodoPayload.buildUpdatedTodo(id, title, completed, userId))
                .when()
                .put(TodoEndpoint.getById(id))
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("title", equalTo(title))
                .body("completed", equalTo(completed))
                .body("userId", equalTo(userId));
    }

    @ParameterizedTest(name = "DELETE todo with ID={0}")
    @Story("DELETE Remove Todo")
    @Description("Verify deleting a todo.")
    @MethodSource("org.example.todos.TodoData#getTodoIds")
    public void testDeleteTodo(int id) {
        given()
                .when()
                .delete(TodoEndpoint.getById(id))
                .then()
                .statusCode(200);
    }
}

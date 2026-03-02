package org.example.todos;

import org.junit.jupiter.params.provider.Arguments;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class TodoData {

    public static Stream<Integer> getTodoIds() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Arguments> getNewTodos() {
        return Stream.of(
                Arguments.of("Sample Todo", false, 1),
                Arguments.of("Another Todo", false, 2),
                Arguments.of("Third Todo", false, 3));
    }

    public static Stream<Arguments> getUpdatedTodos() {
        return Stream.of(
                Arguments.of(1, "Updated Todo", true, 1),
                Arguments.of(2, "Updated Todo 2", true, 1),
                Arguments.of(3, "Updated Todo 3", false, 2));
    }

    public static Map<String, Object> buildNewTodo(String title, boolean completed, int userId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", title);
        payload.put("completed", completed);
        payload.put("userId", userId);
        return payload;
    }

    public static Map<String, Object> buildUpdatedTodo(int id, String title, boolean completed, int userId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("title", title);
        payload.put("completed", completed);
        payload.put("userId", userId);
        return payload;
    }
}

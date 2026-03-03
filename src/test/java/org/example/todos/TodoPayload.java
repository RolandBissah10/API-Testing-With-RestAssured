package org.example.todos;

import java.util.HashMap;
import java.util.Map;

public class TodoPayload {

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

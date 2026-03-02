package org.example.todos;

import java.util.HashMap;
import java.util.Map;

public class TodoData {
    public static Map<String, Object> getNewTodo() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Sample Todo");
        payload.put("completed", false);
        payload.put("userId", 1);
        return payload;
    }

    public static Map<String, Object> getUpdatedTodo(int id) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("title", "Updated Todo");
        payload.put("completed", true);
        payload.put("userId", 1);
        return payload;
    }
}

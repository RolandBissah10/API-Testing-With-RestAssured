package org.example.posts;

import java.util.HashMap;
import java.util.Map;

public class PostPayload {

    public static Map<String, Object> buildNewPost(String title, String body, int userId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", title);
        payload.put("body", body);
        payload.put("userId", userId);
        return payload;
    }

    public static Map<String, Object> buildUpdatedPost(int id, String title, String body, int userId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("title", title);
        payload.put("body", body);
        payload.put("userId", userId);
        return payload;
    }
}

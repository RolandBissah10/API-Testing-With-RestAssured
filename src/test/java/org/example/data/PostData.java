package org.example.data;

import java.util.HashMap;
import java.util.Map;

public class PostData {
    public static Map<String, Object> getNewPost() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Sample Post Title");
        payload.put("body", "Sample Post Body");
        payload.put("userId", 1);
        return payload;
    }

    public static Map<String, Object> getUpdatedPost(int id) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("title", "Updated Post Title");
        payload.put("body", "Updated Post Body");
        payload.put("userId", 1);
        return payload;
    }
}

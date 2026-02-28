package org.example.data;

import java.util.HashMap;
import java.util.Map;

public class CommentData {
    public static Map<String, Object> getNewComment() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "Sample Comment Name");
        payload.put("email", "test@example.com");
        payload.put("body", "Sample Comment Body");
        payload.put("postId", 1);
        return payload;
    }

    public static Map<String, Object> getUpdatedComment(int id) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("name", "Updated Comment Name");
        payload.put("email", "updated@example.com");
        payload.put("body", "Updated Comment Body");
        payload.put("postId", 1);
        return payload;
    }
}

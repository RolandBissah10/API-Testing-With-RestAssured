package org.example.comments;

import java.util.HashMap;
import java.util.Map;

public class CommentPayload {

    public static Map<String, Object> buildNewComment(String name, String email, String body, int postId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", name);
        payload.put("email", email);
        payload.put("body", body);
        payload.put("postId", postId);
        return payload;
    }

    public static Map<String, Object> buildUpdatedComment(int id, String name, String email, String body, int postId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("name", name);
        payload.put("email", email);
        payload.put("body", body);
        payload.put("postId", postId);
        return payload;
    }
}

package org.example.users;

import java.util.HashMap;
import java.util.Map;

public class UserPayload {

    public static Map<String, Object> buildNewUser(String name, String username, String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", name);
        payload.put("username", username);
        payload.put("email", email);
        return payload;
    }

    public static Map<String, Object> buildUpdatedUser(int id, String name, String username, String email) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("name", name);
        payload.put("username", username);
        payload.put("email", email);
        return payload;
    }
}

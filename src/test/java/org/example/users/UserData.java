package org.example.users;

import java.util.HashMap;
import java.util.Map;

public class UserData {
    public static Map<String, Object> getNewUser() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "John Doe");
        payload.put("username", "johndoe");
        payload.put("email", "johndoe@example.com");
        return payload;
    }

    public static Map<String, Object> getUpdatedUser(int id) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("name", "John Smith Updated");
        payload.put("username", "jsmith");
        payload.put("email", "jsmith@example.com");
        return payload;
    }
}

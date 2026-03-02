package org.example.users;

import org.junit.jupiter.params.provider.Arguments;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class UserData {

    public static Stream<Integer> getUserIds() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Arguments> getNewUsers() {
        return Stream.of(
                Arguments.of("John Doe", "johndoe", "johndoe@example.com"),
                Arguments.of("Jane Smith", "janesmith", "janesmith@example.com"),
                Arguments.of("Bob Johnson", "bjohnson", "bjohnson@example.com"));
    }

    public static Stream<Arguments> getUpdatedUsers() {
        return Stream.of(
                Arguments.of(1, "John Smith Updated", "jsmith", "jsmith@example.com"),
                Arguments.of(2, "Jane Doe Updated", "jdoe", "jdoe@example.com"),
                Arguments.of(3, "Bobby Johnson Updated", "bobby", "bobby@example.com"));
    }

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

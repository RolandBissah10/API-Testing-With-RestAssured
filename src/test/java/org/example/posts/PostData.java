package org.example.posts;

import org.junit.jupiter.params.provider.Arguments;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PostData {

    public static Stream<Integer> getPostIds() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Arguments> getNewPosts() {
        return Stream.of(
                Arguments.of("Sample Post Title", "Sample Post Body", 1),
                Arguments.of("Another Post Title", "Another Post Body", 2),
                Arguments.of("Third Post Title", "Third Post Body", 3));
    }

    public static Stream<Arguments> getUpdatedPosts() {
        return Stream.of(
                Arguments.of(1, "Updated Post Title", "Updated Post Body", 1),
                Arguments.of(2, "Updated Post Title 2", "Updated Post Body 2", 1),
                Arguments.of(3, "Updated Post Title 3", "Updated Post Body 3", 2));
    }

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

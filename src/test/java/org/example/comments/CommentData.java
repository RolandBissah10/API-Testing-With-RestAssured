package org.example.comments;

import org.junit.jupiter.params.provider.Arguments;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CommentData {

    public static Stream<Integer> getCommentIds() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Arguments> getNewComments() {
        return Stream.of(
                Arguments.of("Sample Comment Name", "test@example.com", "Sample Comment Body", 1),
                Arguments.of("Another Comment Name", "another@example.com", "Another Comment Body", 2),
                Arguments.of("Third Comment Name", "third@example.com", "Third Comment Body", 3));
    }

    public static Stream<Arguments> getUpdatedComments() {
        return Stream.of(
                Arguments.of(1, "Updated Comment Name", "updated@example.com", "Updated Comment Body", 1),
                Arguments.of(2, "Updated Comment Name 2", "updated2@example.com", "Updated Comment Body 2", 1),
                Arguments.of(3, "Updated Comment Name 3", "updated3@example.com", "Updated Comment Body 3", 2));
    }

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

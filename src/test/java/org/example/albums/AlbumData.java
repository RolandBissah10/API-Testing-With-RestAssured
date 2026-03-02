package org.example.albums;

import org.junit.jupiter.params.provider.Arguments;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class AlbumData {

    public static Stream<Integer> getAlbumIds() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Arguments> getNewAlbums() {
        return Stream.of(
                Arguments.of("Sample Album Title", 1),
                Arguments.of("Another Album Title", 2),
                Arguments.of("Third Album Title", 3));
    }

    public static Stream<Arguments> getUpdatedAlbums() {
        return Stream.of(
                Arguments.of(1, "Updated Album Title", 1),
                Arguments.of(2, "Updated Album Title 2", 1),
                Arguments.of(3, "Updated Album Title 3", 2));
    }

    public static Map<String, Object> buildNewAlbum(String title, int userId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", title);
        payload.put("userId", userId);
        return payload;
    }

    public static Map<String, Object> buildUpdatedAlbum(int id, String title, int userId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("title", title);
        payload.put("userId", userId);
        return payload;
    }
}

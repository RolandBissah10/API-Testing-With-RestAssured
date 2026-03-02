package org.example.photos;

import org.junit.jupiter.params.provider.Arguments;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PhotoData {

    public static Stream<Integer> getPhotoIds() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Arguments> getNewPhotos() {
        return Stream.of(
                Arguments.of("Sample Photo Title", "https://via.placeholder.com/600/92c952",
                        "https://via.placeholder.com/150/92c952", 1),
                Arguments.of("Another Photo Title", "https://via.placeholder.com/600/771796",
                        "https://via.placeholder.com/150/771796", 1),
                Arguments.of("Third Photo Title", "https://via.placeholder.com/600/24f355",
                        "https://via.placeholder.com/150/24f355", 2));
    }

    public static Stream<Arguments> getUpdatedPhotos() {
        return Stream.of(
                Arguments.of(1, "Updated Photo Title", "https://via.placeholder.com/600/92c952",
                        "https://via.placeholder.com/150/92c952", 1),
                Arguments.of(2, "Updated Photo Title 2", "https://via.placeholder.com/600/771796",
                        "https://via.placeholder.com/150/771796", 1),
                Arguments.of(3, "Updated Photo Title 3", "https://via.placeholder.com/600/24f355",
                        "https://via.placeholder.com/150/24f355", 2));
    }

    public static Map<String, Object> buildNewPhoto(String title, String url, String thumbnailUrl, int albumId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", title);
        payload.put("url", url);
        payload.put("thumbnailUrl", thumbnailUrl);
        payload.put("albumId", albumId);
        return payload;
    }

    public static Map<String, Object> buildUpdatedPhoto(int id, String title, String url, String thumbnailUrl,
            int albumId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("title", title);
        payload.put("url", url);
        payload.put("thumbnailUrl", thumbnailUrl);
        payload.put("albumId", albumId);
        return payload;
    }
}

package org.example.photos;

import java.util.HashMap;
import java.util.Map;

public class PhotoPayload {

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

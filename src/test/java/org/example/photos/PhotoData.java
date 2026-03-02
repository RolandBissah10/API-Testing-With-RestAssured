package org.example.photos;

import java.util.HashMap;
import java.util.Map;

public class PhotoData {
    public static Map<String, Object> getNewPhoto() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Sample Photo Title");
        payload.put("url", "https://via.placeholder.com/600/92c952");
        payload.put("thumbnailUrl", "https://via.placeholder.com/150/92c952");
        payload.put("albumId", 1);
        return payload;
    }

    public static Map<String, Object> getUpdatedPhoto(int id) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("title", "Updated Photo Title");
        payload.put("url", "https://via.placeholder.com/600/92c952");
        payload.put("thumbnailUrl", "https://via.placeholder.com/150/92c952");
        payload.put("albumId", 1);
        return payload;
    }
}

package org.example.data;

import java.util.HashMap;
import java.util.Map;

public class AlbumData {
    public static Map<String, Object> getNewAlbum() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Sample Album Title");
        payload.put("userId", 1);
        return payload;
    }

    public static Map<String, Object> getUpdatedAlbum(int id) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("title", "Updated Album Title");
        payload.put("userId", 1);
        return payload;
    }
}

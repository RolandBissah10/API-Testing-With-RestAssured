package org.example.albums;

import java.util.HashMap;
import java.util.Map;

public class AlbumPayload {

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

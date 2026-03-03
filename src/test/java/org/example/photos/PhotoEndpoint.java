package org.example.photos;

public class PhotoEndpoint {
    public static final String PHOTOS = "/photos";

    public static String getById(int id) {
        return PHOTOS + "/" + id;
    }
}

package org.example.albums;

public class AlbumEndpoint {
    public static final String ALBUMS = "/albums";

    public static String getById(int id) {
        return ALBUMS + "/" + id;
    }
}

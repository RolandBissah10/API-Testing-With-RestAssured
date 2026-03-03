package org.example.posts;

public class PostEndpoint {
    public static final String POSTS = "/posts";

    public static String getById(int id) {
        return POSTS + "/" + id;
    }
}

package org.example.comments;

public class CommentEndpoint {
    public static final String COMMENTS = "/comments";

    public static String getById(int id) {
        return COMMENTS + "/" + id;
    }
}

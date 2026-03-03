package org.example.users;

public class UserEndpoint {
    public static final String USERS = "/users";

    public static String getById(int id) {
        return USERS + "/" + id;
    }
}

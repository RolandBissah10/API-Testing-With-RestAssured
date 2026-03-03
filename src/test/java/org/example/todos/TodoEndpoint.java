package org.example.todos;

public class TodoEndpoint {
    public static final String TODOS = "/todos";

    public static String getById(int id) {
        return TODOS + "/" + id;
    }
}

package org.example.todos;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TodoData {

    public static Stream<Integer> getTodoIds() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Arguments> getNewTodos() {
        return Stream.of(
                Arguments.of("Sample Todo", false, 1),
                Arguments.of("Another Todo", false, 2),
                Arguments.of("Third Todo", false, 3));
    }

    public static Stream<Arguments> getUpdatedTodos() {
        return Stream.of(
                Arguments.of(1, "Updated Todo", true, 1),
                Arguments.of(2, "Updated Todo 2", true, 1),
                Arguments.of(3, "Updated Todo 3", false, 2));
    }
}

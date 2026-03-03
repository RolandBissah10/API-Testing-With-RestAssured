package org.example.albums;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class AlbumData {

    public static Stream<Integer> getAlbumIds() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Arguments> getNewAlbums() {
        return Stream.of(
                Arguments.of("Sample Album Title", 1),
                Arguments.of("Another Album Title", 2),
                Arguments.of("Third Album Title", 3));
    }

    public static Stream<Arguments> getUpdatedAlbums() {
        return Stream.of(
                Arguments.of(1, "Updated Album Title", 1),
                Arguments.of(2, "Updated Album Title 2", 1),
                Arguments.of(3, "Updated Album Title 3", 2));
    }
}

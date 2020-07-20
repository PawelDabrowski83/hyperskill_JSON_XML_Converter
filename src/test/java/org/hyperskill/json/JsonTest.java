package org.hyperskill.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class JsonTest {

    @DisplayName("Should toString work properly?")
    @ParameterizedTest(name = "{index} => expected={0}, given={1}")
    @MethodSource("toStringArgumentsProvider")
    void toString(String expected, Jsonish given) {
        assertEquals(expected, given.toString());
    }
    private static Stream<Arguments> toStringArgumentsProvider() {
        return Stream.of(
                Arguments.of("{\n" +
                        "\t\"element\" : \"content\"\n" +
                        "}",
                        new Json("element", "content")
                ),
                Arguments.of("{\n" +
                        "\t\"cat\" : null\n" +
                        "}",
                        new Json("cat")
                )
        );
    }
}

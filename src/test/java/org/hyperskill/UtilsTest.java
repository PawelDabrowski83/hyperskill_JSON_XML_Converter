package org.hyperskill;

import netscape.javascript.JSObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @DisplayName("Should jsonToXml work")
    @ParameterizedTest(name = "{index} => expected={0}, json={1}")
    @MethodSource("jsonToXmlArgumentsProvider")
    void jsonToXml(String expected, String json) {
        assertEquals(expected, Utils.jsonToXml(json));
    }
    private static Stream<Arguments> jsonToXmlArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        "<jdk>1.8.9</jdk>",
                        "{\"jdk\" : \"1.8.9\"}"
                ),
                Arguments.of(
                        "<host>127.0.0.1</host>",
                        "{\"host\":\"127.0.0.1\"}"
                ),
                Arguments.of(
                        "<success/>",
                        "{\"success\": null }"
                ),
                Arguments.of(
                        "<storage/>",
                        "{ \"storage\" : null }"
                )
        );
    }
}

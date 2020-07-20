package org.hyperskill;

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
                ),
                Arguments.of(
                        "",
                        ""
                )
        );
    }

    @DisplayName("Should xmlToJson work")
    @ParameterizedTest(name = "{index} => expected={0}, xml={1}")
    @MethodSource("xmlToJsonArgumentsProvider")
    void xmlToJson(String expected, String xml) {
        assertEquals(expected, Utils.xmlToJson(xml));
    }
    private static Stream<Arguments> xmlToJsonArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        "{\n\t\"storage\" : null\n}",
                        "<storage/>"
                ),
                Arguments.of(
                        "",
                        ""
                ),
                Arguments.of(
                        "{\n\t\"host\" : \"127.0.0.1\"\n}",
                        "<host>127.0.0.1</host>"
                ),
                Arguments.of(
                        "{\n\t\"jdk\" : \"1.8.9\"\n}",
                        "<jdk>1.8.9</jdk>"
                ),
                Arguments.of(
                        "{\n" +
                                "\t\"element_name\" : {\n" +
                                "\t\t\"@attribute1\" : \"attribute1_value\",\n" +
                                "\t\t" + "\"@attributeN\" : \"attributeN_value\",\n" +
                                "\t\t" + "\"#element\" : \"content\"\n" +
                                "\t" + "}\n" +
                                "}",
                        "<element attribute1 = \"attribute1_value\" attributeN= \"attributeN_value\">content</element>"
                )
        );
    }

    @DisplayName("Should indent() work, adding next level of tabulation")
    @ParameterizedTest(name = "{index} => expected={0}, given={1}")
    @MethodSource("indentArgumentsProvider")
    void indent(String expected, String given) {
        assertEquals(expected, Utils.indent(given));
    }
    private static Stream<Arguments> indentArgumentsProvider() {
        return Stream.of(
                Arguments.of("\tcat", "cat"),
                Arguments.of("\ttak\n" +
                        "\t\tnie",
                        "tak\n" +
                        "\tnie"),
                Arguments.of("", ""),
                Arguments.of("\tone\n" +
                        "\t\ttwo\n" +
                        "\t\t\tthree",
                        "one\n" +
                        "\ttwo\n" +
                        "\t\tthree")
        );
    }
}

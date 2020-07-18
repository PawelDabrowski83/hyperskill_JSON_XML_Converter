package org.hyperskill.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class XmlTest {

    @DisplayName("should customized toString() work")
    @ParameterizedTest(name = "{index} => expected={0}, given={1}")
    @MethodSource("toStringArgumentsProvider")
    void toString(String expected, Xml given) {
        assertEquals(expected, given.toString());
    }
    private static Stream<Arguments> toStringArgumentsProvider() {
        return Stream.of(
                Arguments.of("<zonk/>", new Xml("zonk")),
                Arguments.of("<null/>", new Xml("null")),
                Arguments.of("<book>Lorem ipsum</book>", new Xml("book", "Lorem ipsum")),
                Arguments.of("<house>Duras</house>", new Xml("house", "Duras")),
                Arguments.of(
                        "<book title='Thinking in Java' author='Bruce Eckel'/>",
                        new Xml("book", null, Set.of(
                                new XmlAttribute("title", "Thinking in Java"),
                                new XmlAttribute("author", "Bruce Eckel")
                                )
                        )
                ),
                Arguments.of(
                        "<book title='Thinking in Java'/>",
                        new Xml("book", null, Set.of(
                                new XmlAttribute("title", "Thinking in Java")
                        ))
                ),
                Arguments.of(
                        "<book author='Bruce Eckel'>Thinking in Java</book>",
                        new Xml("book", "Thinking in Java", Set.of(new XmlAttribute("author", "Bruce Eckel")))
                )
        );
    }
}

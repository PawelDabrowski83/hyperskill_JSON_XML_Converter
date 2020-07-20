package org.hyperskill;

import org.hyperskill.xml.Xml;
import org.hyperskill.xml.Xmlish;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {
    protected static final Pattern JSON_PATTERN = Pattern.compile("\\{\\s*\"(\\S+)\"\\s*:\\s*\"(\\S+)\"|\\s*\"(\\S+)\"\\s*:\\s*(null)\\s*}");
    protected static final Pattern XML_PATTERN = Pattern.compile("<(\\S+)/>|<(\\S+)>(\\S+)</\\2>");
    protected static final Pattern XML_ATTRIBUTES = Pattern.compile("(\\S+)\\s*=\\s*[\"'](\\S+)[\"']");
    protected static final Pattern XML_EXTENDED = Pattern.compile("(\\S+)\\s*=\\s*[\"'](\\S+)[\"']|<(\\w+)|>(\\w+)");
    protected static final Logger logger = Logger.getLogger(Utils.class.getName());
    protected static FileHandler fh;

    public static String jsonToXml(String json) {
        logger.setLevel(Level.FINE);
        try {
            fh = new FileHandler("logs/Utils.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.fine("method jsonToXML (String json) >>> INPUT: " + json);

        Matcher matcher = JSON_PATTERN.matcher(json);
        logger.fine("matcher with regex " + JSON_PATTERN);
        if (!matcher.find()) {
            logger.fine("match not found, returning empty string");
            return "";
        }
        Xmlish xml;
        if ("null".equals(matcher.group(4))) {
            xml = new Xml(matcher.group(3));
        } else {
            xml = new Xml(matcher.group(1), matcher.group(2));
        }

        logger.fine("String OUTPUT: " + xml.toString());
        return xml.toString();
    }

    public static String xmlToJson(String xml) {
        logger.setLevel(Level.FINE);
        try {
            fh = new FileHandler("logs/Utils.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.fine("method xmlToJson(String xml) >> INPUT: " + xml);
        Matcher matcher = XML_PATTERN.matcher(xml);
        if (!matcher.find()) {
            logger.fine("Cannot match xml to regex");
            logger.fine("OUTPUT: (empty)");
            return "";
        }

        String key;
        String value = "";
        String result;

        if (matcher.group(1) == null) {
            key = matcher.group(2);
            value = matcher.group(3);
            result = String.format("{\n\t\"%s\" : \"%s\"\n}", key, value);
        } else {
            key = matcher.group(1);
            result = String.format("{\n\t\"%s\" : null\n}", key);
        }

        logger.fine("OUTPUT: " + result);
        return result;
    }

    /**
     * Add indentation to String
     * @param given one/multiline string
     * @return indented string
     */
    protected static String indent(String given) {
        if (given == null || given.isEmpty()) {
            return "";
        }
        return given.lines().map(n -> "\t" + n).collect(Collectors.joining(System.lineSeparator()));
    }
}

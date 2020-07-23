package org.hyperskill;

import org.hyperskill.json.Json;
import org.hyperskill.json.Jsonish;
import org.hyperskill.xml.Xml;
import org.hyperskill.xml.XmlAttribute;
import org.hyperskill.xml.Xmlish;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {
    protected static final Pattern JSON_PATTERN = Pattern.compile("\\{\\s*\"(.+)\"\\s*:\\s*\"(.+)\"\\s*}|\\{\\s*\"(.+)\"\\s*:\\s*null\\s*}");
    protected static final Pattern XML_PATTERN = Pattern.compile("(\\S+)\\s*=\\s*[\"'](.*?)[\"']|<(\\w+)");
    protected static final Pattern XML_ATTRIBUTES = Pattern.compile("(\\S+)\\s*=\\s*[\"'](\\S+)[\"']");
    protected static final Pattern XML_CONTENT = Pattern.compile("<\\w+.*>(.*)</\\w+>");
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

        Matcher matcher = JSON_PATTERN.matcher(json.replaceAll("[\\t\\n]", ""));
        logger.fine("matcher with regex " + JSON_PATTERN);
        if (!matcher.find()) {
            logger.fine("match not found, returning empty string");
            return "";
        }
        Xmlish xml;
        Set<XmlAttribute> attributes = new HashSet<>();


        if (matcher.group(3) != null) {
            xml = new Xml(matcher.group(3));
        } else {
            xml = new Xml(matcher.group(1), matcher.group(2));
        }

        logger.fine("String OUTPUT: " + xml.toString());
        return xml.toString();
    }

    /**
     * convert xml to json string
     * @param xml string in valid xml format
     * @return string in json format
     */
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
//        Matcher matcher = XML_PATTERN.matcher(xml);
//        if (!matcher.find()) {
//            logger.fine("Cannot match xml to regex");
//            return "";
//        }
        Matcher matcher = XML_CONTENT.matcher(xml);
        String content = "";
        if (!matcher.matches()) {
            logger.fine("Cannot match xml to regex " + XML_CONTENT);
        } else {
            content = matcher.group(1);
        }

        matcher = XML_PATTERN.matcher(xml);
        if (!matcher.find()) {
            logger.fine("Cannot match xml to regex");
            return "";
        }

        Jsonish json;

        if (content.isEmpty()) {
            json = new Json(matcher.group(3));
        } else {
            json = new Json(matcher.group(3), content);
        }

        logger.fine("OUTPUT: " + json.toString());
        return json.toString();
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

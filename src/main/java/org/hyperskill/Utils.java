package org.hyperskill;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    protected static final Pattern JSON_PATTERN = Pattern.compile("\\{\\s*\"(\\S+)\"\\s*:\\s*\"(\\S+)\"|\\s*\"(\\S+)\"\\s*:\\s*(null)\\s*}");
    protected static final Logger logger = Logger.getLogger(Utils.class.getName());



    public static String jsonToXml(String json) {
        FileHandler fh;
        logger.setLevel(Level.FINE);
        try {
            fh = new FileHandler("logs/Utils.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.fine("method jsonToXML (String json)");
        logger.fine("INPUT: " + json);
        Matcher matcher = JSON_PATTERN.matcher(json);
        logger.fine("matcher with regex " + JSON_PATTERN);
        if (!matcher.find()) {
            logger.fine("match not found, returning empty string");
            return "";
        }
        String key;
        String value = null;
        logger.fine("check if json has field with null value");
        if ("null".equals(matcher.group(4))) {
            key = matcher.group(3);
            logger.fine("null detected, setting key to " + key);
        } else {
            key = matcher.group(1);
            value = matcher.group(2);
            logger.fine("null not detected, setting key to: " + key + " and value to: " + value);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("<");
        builder.append(key);
        if (value != null) {
            builder.append(">");
            builder.append(value);
            builder.append("</");
            builder.append(key);
            builder.append(">");
        } else {
            builder.append("/>");
        }
        String result = builder.toString();
        logger.fine("String OUTPUT: " + result);
        return result;
    }
}

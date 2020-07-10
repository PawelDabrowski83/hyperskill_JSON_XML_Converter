package org.hyperskill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    protected static final Pattern JSON_PATTERN = Pattern.compile("\\{\\s*\"(\\S+)\"\\s*:\\s*\"(\\S+)\"|\\s*\"(\\S+)\"\\s*:\\s*(null)\\s*}");
    public static String jsonToXml(String json) {
        Matcher matcher = JSON_PATTERN.matcher(json);
        if (!matcher.find()) {
            return "";
        }
        String key;
        String value = null;
        if ("null".equals(matcher.group(4))) {
            key = matcher.group(3);
        } else {
            key = matcher.group(1);
            value = matcher.group(2);
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

        return builder.toString();
    }
}

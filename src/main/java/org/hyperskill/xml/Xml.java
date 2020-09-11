package org.hyperskill.xml;

import java.util.*;

public class Xml implements Xmlish{

    private final String key;
    private final String value;
    private final List<XmlAttribute> attributes;

    public Xml(String key) {
        this.key = key;
        this.value = null;
        this.attributes = new ArrayList<>();
    }

    public Xml(String key, List<XmlAttribute> attributes) {
        this.key = key;
        this.value = null;
        this.attributes = attributes;
    }

    public Xml(String key, String value) {
        this.key = key;
        this.value = value;
        this.attributes = new ArrayList<>();
    }

    public Xml(String key, String value, List<XmlAttribute> attributes) {
        this.key = key;
        this.value = value;
        this.attributes = attributes;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public List<XmlAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Xml)) return false;

        Xml xml = (Xml) o;

        if (!getKey().equals(xml.getKey())) return false;
        if (getValue() != null ? !getValue().equals(xml.getValue()) : xml.getValue() != null) return false;
        return getAttributes() != null ? getAttributes().equals(xml.getAttributes()) : xml.getAttributes() == null;
    }

    @Override
    public int hashCode() {
        int result = getKey().hashCode();
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + (getAttributes() != null ? getAttributes().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String attribute = null;
        if (attributes != null && !attributes.isEmpty()) {
            attribute = getAttributes().toString().replaceAll("[\\[\\],]", "");
        }
        if (value == null || value.isEmpty()) {
            if (attribute == null) {
                return String.format("<%s/>", key);
            } else {
                return String.format("<%s %s/>", key, attribute);
            }
        } else {
            if (attribute == null) {
                return String.format("<%s>%s</%s>", key, value, key);
            } else {
                return String.format("<%s %s>%s</%s>", key, attribute, value, key);
            }
        }
    }
}

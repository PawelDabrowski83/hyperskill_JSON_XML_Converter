package org.hyperskill.xml;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Xml implements Xmlish{

    private final String key;
    private final String value;
    private final Set<XmlAttribute> attributes;

    public Xml(String key) {
        this.key = key;
        this.value = null;
        this.attributes = new HashSet<>();
    }

    public Xml(String key, String value) {
        this.key = key;
        this.value = value;
        this.attributes = new HashSet<>();
    }

    public Xml(String key, String value, Set<XmlAttribute> attributes) {
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

    public Set<XmlAttribute> getAttributes() {
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
        if (value == null || value.isEmpty()) {
            return String.format("<%s/>", key);
        }
        if (attributes == null || attributes.isEmpty()) {
            return String.format("<%s>%s</%s>", key, value, key);
        }
        String attributes = getAttributes().toString();
        return String.format("<%s %s>%s</%s>", key, attributes, value, key);
    }
}

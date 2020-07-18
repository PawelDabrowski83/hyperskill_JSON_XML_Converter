package org.hyperskill.xml;

public class XmlAttribute {

    private final String key;
    private final String value;

    public XmlAttribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof XmlAttribute)) return false;

        XmlAttribute that = (XmlAttribute) o;

        if (!getKey().equals(that.getKey())) return false;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        int result = getKey().hashCode();
        result = 31 * result + getValue().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s='%s'", key, value);
    }
}

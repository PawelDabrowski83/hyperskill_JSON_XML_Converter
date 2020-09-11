package org.hyperskill.xml;

import java.util.List;

public class XmlContainer implements Xmlish {
    private final String key;
    private final List<Xmlish> content;

    public XmlContainer(String key, List<Xmlish> content) {
        this.key = key;
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return null;
    }

    public List<Xmlish> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof XmlContainer)) return false;

        XmlContainer that = (XmlContainer) o;

        if (!getKey().equals(that.getKey())) return false;
        return getContent() != null ? getContent().equals(that.getContent()) : that.getContent() == null;
    }

    @Override
    public int hashCode() {
        int result = getKey().hashCode();
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "XmlContainer{" +
                "key='" + key + '\'' +
                ", content=" + content +
                '}';
    }
}

package org.hyperskill.xml;

import java.util.Set;

public class XmlContainer implements Xmlish {
    private final String key;
    private final Set<Xmlish> content;

    public XmlContainer(String key, Set<Xmlish> content) {
        this.key = key;
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public Set<Xmlish> getContent() {
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

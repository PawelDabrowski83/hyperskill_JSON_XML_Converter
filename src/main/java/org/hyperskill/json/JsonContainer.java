package org.hyperskill.json;

import java.util.List;
import java.util.Set;

public class JsonContainer implements Jsonish{

    protected final String key;
    protected final List<Jsonish> content;

    public JsonContainer(String key, List<Jsonish> content) {
        this.key = key;
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public List<Jsonish> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JsonContainer)) return false;

        JsonContainer that = (JsonContainer) o;

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
        return "JsonContainer{" +
                "key='" + key + '\'' +
                ", content=" + content +
                '}';
    }
}

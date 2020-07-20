package org.hyperskill.json;

public class Json implements Jsonish{

    private final String key;
    private final String value;

    public Json(String key) {
        this.key = key;
        this.value = null;
    }

    public Json(String key, String value) {
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
        if (!(o instanceof Json)) return false;

        Json json = (Json) o;

        if (!getKey().equals(json.getKey())) return false;
        return getValue() != null ? getValue().equals(json.getValue()) : json.getValue() == null;
    }

    @Override
    public int hashCode() {
        int result = getKey().hashCode();
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        if (value == null || value.isEmpty() || "null".equals(value)) {
            return String.format("{\n" +
                    "\t\"%s\" : %s\n" +
                    "}", key, value);
        }
        return String.format("{\n" +
                "\t\"%s\" : \"%s\"\n" +
                "}", key, value);
    }
}

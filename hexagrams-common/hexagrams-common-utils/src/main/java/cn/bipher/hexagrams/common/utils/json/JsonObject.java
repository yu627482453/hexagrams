package cn.bipher.hexagrams.common.utils.json;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * JsonObject json对象
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/18 9:46
 */
public class JsonObject extends LinkedHashMap<String, Object> implements Cloneable, Serializable {

    private static final long serialVersionUID = 4489366607809686388L;

    public String getString(String key) {
        Object value = this.get(key);
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    public Long getLong(String key) {
        Object value = this.get(key);
        if (value != null) {
            return Long.valueOf(String.valueOf(value));
        }
        return null;
    }

    public Integer getInteger(String key) {
        Object value = this.get(key);
        if (value != null) {
            return Integer.valueOf(String.valueOf(value));
        }
        return null;
    }

    public Date getDate(String key) {
        Object value = this.get(key);
        if (value != null) {
            return (Date) this.get(key);
        }
        return null;
    }

    public BigDecimal getBigDecimal(String key) {
        Object value = this.get(key);
        if (value != null) {
            return new BigDecimal(String.valueOf(value));
        }
        return null;
    }

    public Boolean getBoolean(String key) {
        Object value = this.get(key);
        if (value != null) {
            return Boolean.valueOf(String.valueOf(value));
        }
        return null;
    }

    public JsonArray getJsonArray(String key) {
        Object value = this.get(key);
        if (value != null) {
            return (JsonArray) this.get(key);
        }
        return null;
    }

    public JsonObject getJsonObject(String key) {
        Object value = this.get(key);
        if (value != null) {
            return (JsonObject) this.get(key);
        }
        return null;
    }
}
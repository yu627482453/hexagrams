package cn.bipher.hexagrams.common.utils.json;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * JsonArray json数组
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/18 9:47
 */
public class JsonArray  extends ArrayList<Object> implements Cloneable, Serializable {
    private static final long serialVersionUID = -8794901953787955575L;

    public JsonObject getJsonObject(int index) {
        return (JsonObject)this.get(index);
    }
    public String getString(int index) {
        Object value = this.get(index);
        if (value != null) {
            return value.toString();
        }
        return null;
    }
    public Long getLong(int index){
        return (Long) this.get(index);
    }
    public Integer getInteger(int index){
        return (Integer) this.get(index);
    }
    public Date getDate(int index){
        return (Date) this.get(index);
    }
    public BigDecimal getBigDecimal(int index){
        return (BigDecimal) this.get(index);
    }
    public Boolean getBoolean(int index){
        return (Boolean) this.get(index);
    }
    public JsonArray getJsonArray(int index) {
        return (JsonArray) this.get(index);
    }
}

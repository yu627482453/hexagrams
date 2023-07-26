package cn.bipher.hexagrams.common.utils.util;

import cn.bipher.hexagrams.jackson.JacksonJsonTool;
import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.Type;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 14:48
 */
public class JsonUtil {

    static JacksonJsonTool jsonTool = new JacksonJsonTool();

    public static String toJson(Object obj) {
        return jsonTool.toJson(obj);
    }

    public static <T> T toObj(String json, Class<T> r) {
        return jsonTool.toObj(json, r);
    }

    public static <T> T toObj(String json, Type t) {
        return jsonTool.toObj(json, t);
    }

    public static <T> T toObj(String json, TypeReference<T> t) {
        return jsonTool.toObj(json, t);
    }

}
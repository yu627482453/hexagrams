package cn.bipher.hexagrams.common.utils.util;


import cn.bipher.hexagrams.common.utils.json.JsonArray;
import cn.bipher.hexagrams.common.utils.json.JsonObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * JacksonUtil
 *
 * @author Bipher
 * @version 1
 * @date 2023/2/18 9:47
 */
@Slf4j
public class JacksonUtil {
    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    private static String timeZone = "GMT+8";
    private static ObjectMapper objectMapper, jsonObjectMapper;

    static {
        objectMapper = new ObjectMapper();
        // 设置时区
        objectMapper.setTimeZone(TimeZone.getTimeZone(timeZone));
        // 设置时间格式
        objectMapper.setDateFormat(new SimpleDateFormat(pattern));


        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        //objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        objectMapper.registerModule(swapperObjectMapper());
        jsonObjectMapper = registerJsonArray(objectMapper);
    }


    public JacksonUtil(@Autowired ObjectMapper jacksonObjectMapper,
                       @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}") String jacksonPattern,
                       @Value("${spring.jackson.time-zone:GMT+8}") String jacksonTimeZone) {
        pattern = jacksonPattern;
        timeZone = jacksonTimeZone;
        objectMapper = jacksonObjectMapper;
        jsonObjectMapper = registerJsonArray(objectMapper);
    }


    public static JavaTimeModule swapperObjectMapper() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        /** 序列化配置,针对java8 时间 **/
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"
        )));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        /** 反序列化配置,针对java8 时间 **/
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy" +
                "-MM-dd")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm" +
                ":ss")));

        return javaTimeModule;
    }

    /**
     * 注册List类新处理类型
     *
     * @param objectMapper
     * @return 新的objectMapper
     */
    private static ObjectMapper registerJsonArray(ObjectMapper objectMapper) {
        ObjectMapper jsonObjectMapper = objectMapper.copy();
        SimpleModule useJsonArray = new SimpleModule("UseJsonArray");
        useJsonArray.addAbstractTypeMapping(List.class, JsonArray.class);
        useJsonArray.addAbstractTypeMapping(Map.class, JsonObject.class);
        jsonObjectMapper.registerModule(useJsonArray);
        return jsonObjectMapper;
    }

    /**
     * 对象转Json格式字符串
     *
     * @param obj 对象
     * @return Json格式字符串
     */
    @SneakyThrows
    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
    }

    /**
     * 对象转map
     *
     * @param obj 对象
     * @return map
     */
    @SneakyThrows
    public static <T> Map<String, Object> obj2Map(T obj) {
        if (obj == null) {
            return null;
        }
        return objectMapper.readValue(obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj),
                Map.class);
    }

    /**
     * 对象转bytes
     *
     * @param obj 对象
     * @return map
     */
    @SneakyThrows
    public static <T> byte[] obj2Bytes(T obj) {
        if (obj == null) {
            return null;
        }
        return objectMapper.writeValueAsBytes(obj);
    }

    /**
     * 对象转Json格式字符串(格式化的Json字符串)
     *
     * @param obj 对象
     * @return 美化的Json格式字符串
     */
    @SneakyThrows
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        return obj instanceof String ? (String) obj :
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    /**
     * 字符串转换为自定义对象
     *
     * @param str   要转换的字符串
     * @param clazz 自定义对象的class对象
     * @return 自定义对象
     */
    @SneakyThrows
    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
    }

    /**
     * 字符串转Map
     *
     * @param str
     * @return
     */
    public static Map string2Map(String str) {
        return string2Obj(str, Map.class);
    }

    /**
     * 字符串转JsonObject
     *
     * @param str
     * @return
     */
    @SneakyThrows
    public static JsonObject string2JsonObject(String str) {
        return jsonObjectMapper.readValue(str, JsonObject.class);
    }

    @SneakyThrows
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
    }

    @SneakyThrows
    public static <T> T string2Obj(String str, Class<?> collectionClazz, Class<?>... elementClazzes) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClazzes);
        return objectMapper.readValue(str, javaType);
    }
}

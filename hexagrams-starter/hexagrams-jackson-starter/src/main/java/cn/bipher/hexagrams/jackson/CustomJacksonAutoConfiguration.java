package cn.bipher.hexagrams.jackson;

import cn.bipher.hexagrams.jackson.config.CustomJavaTimeModule;
import cn.bipher.hexagrams.jackson.config.NullSerializerProvider;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 11:24
 */
@AutoConfiguration(before = JacksonAutoConfiguration.class)
public class CustomJacksonAutoConfiguration {

    /**
     * 自定义objectMapper
     * @return ObjectMapper
     */
    @Bean
    @ConditionalOnClass(ObjectMapper.class)
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        // org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.JacksonObjectMapperConfiguration
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 对于空对象的序列化不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 序列化时忽略未知属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // NULL值修改
        objectMapper.setSerializerProvider(new NullSerializerProvider());
        // 有特殊需要转义字符, 不报错
        objectMapper.enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature());
        // 更新 JsonUtils 中的 ObjectMapper，保持容器和工具类中的 ObjectMapper 对象一致
        JacksonJsonTool.setMapper(objectMapper);

        return objectMapper;
    }

    /**
     * 注册自定义 的 jackson 时间格式，高优先级，用于覆盖默认的时间格式
     * @return CustomJavaTimeModule
     */
    @Bean
    @ConditionalOnMissingBean(CustomJavaTimeModule.class)
    public CustomJavaTimeModule customJavaTimeModule() {
        return new CustomJavaTimeModule();
    }


}

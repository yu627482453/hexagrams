package cn.bipher.hexagrams.common.utils.autoconfigure;

import cn.bipher.hexagrams.common.utils.util.JacksonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * jackson utils 自动装载类
 * @author Bipher
 * @version 1
 * @date 2023/2/18 10:46
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore({JacksonAutoConfiguration.class, JacksonUtil.class})
@Import(JacksonUtil.class)
public class JacksonUtilAutoConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.modulesToInstall(JacksonUtil.swapperObjectMapper());
    }
}
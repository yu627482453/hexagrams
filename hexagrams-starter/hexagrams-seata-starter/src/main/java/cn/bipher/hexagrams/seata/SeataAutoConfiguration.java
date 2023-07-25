package cn.bipher.hexagrams.seata;

import cn.bipher.hexagrams.common.core.factory.YamlPropertySourceFactory;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/25 22:55
 */
@PropertySource(value = "classpath:seata-config.yml", factory = YamlPropertySourceFactory.class)
@EnableAutoDataSourceProxy(useJdkProxy = true)
@Configuration(proxyBeanMethods = false)
public class SeataAutoConfiguration {
}
package cn.bipher.hexagrams.redis.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/25 22:01
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "hexagrams.cache-manager")
public class CacheManagerProperties {

    private List<CacheConfig> configs;

    @Setter
    @Getter
    public static class CacheConfig {
        /**
         * cache key
         */
        private String key;
        /**
         * 过期时间，sec
         */
        private long second = 60;
    }
}
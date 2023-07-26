package cn.bipher.hexagrams.redis.autoconfigure;

import cn.bipher.hexagrams.redis.RedisHelper;
import cn.bipher.hexagrams.redis.config.CacheProperties;
import cn.bipher.hexagrams.redis.config.CachePropertiesHolder;
import cn.bipher.hexagrams.redis.core.CacheStringAspect;
import cn.bipher.hexagrams.redis.prefix.IRedisPrefixConverter;
import cn.bipher.hexagrams.redis.prefix.impl.DefaultRedisPrefixConverter;
import cn.bipher.hexagrams.redis.serialize.CacheSerializer;
import cn.bipher.hexagrams.redis.serialize.JacksonSerializer;
import cn.bipher.hexagrams.redis.serialize.PrefixJdkRedisSerializer;
import cn.bipher.hexagrams.redis.serialize.PrefixStringRedisSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis 自动配置类
 *
 * @author Hccake
 * @version 1.0
 * @date 2019/9/2 14:13
 */
@AutoConfiguration(before = RedisAutoConfiguration.class)
@RequiredArgsConstructor
@EnableConfigurationProperties(CacheProperties.class)
public class HexagramsRedisAutoConfiguration {

	private final RedisConnectionFactory redisConnectionFactory;

	/**
	 * 初始化配置类
	 * @return GlobalCacheProperties
	 */
	@Bean
	@ConditionalOnMissingBean
	public CachePropertiesHolder cachePropertiesHolder(CacheProperties cacheProperties) {
		CachePropertiesHolder cachePropertiesHolder = new CachePropertiesHolder();
		cachePropertiesHolder.setCacheProperties(cacheProperties);
		return cachePropertiesHolder;
	}

	/**
	 * 默认使用 Jackson 序列化
	 * @param objectMapper objectMapper
	 * @return JacksonSerializer
	 */
	@Bean
	@ConditionalOnMissingBean
	public CacheSerializer cacheSerializer(ObjectMapper objectMapper) {
		return new JacksonSerializer(objectMapper);
	}

	/**
	 * redis key 前缀处理器
	 * @return IRedisPrefixConverter
	 */
	@Bean
	@DependsOn("cachePropertiesHolder")
	@ConditionalOnProperty(prefix = "hexagrams.redis", name = "key-prefix")
	@ConditionalOnMissingBean(IRedisPrefixConverter.class)
	public IRedisPrefixConverter redisPrefixConverter() {
		return new DefaultRedisPrefixConverter(CachePropertiesHolder.keyPrefix());
	}

	@Bean
	@ConditionalOnBean(IRedisPrefixConverter.class)
	@ConditionalOnMissingBean
	public StringRedisTemplate stringRedisTemplate(IRedisPrefixConverter redisPrefixConverter) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new PrefixStringRedisSerializer(redisPrefixConverter));
		return template;
	}

	@Bean
	@ConditionalOnBean(IRedisPrefixConverter.class)
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(IRedisPrefixConverter redisPrefixConverter) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new PrefixJdkRedisSerializer(redisPrefixConverter));
		return template;
	}

	@Bean
	@ConditionalOnMissingBean(RedisHelper.class)
	public RedisHelper redisHelper(StringRedisTemplate template) {
		RedisHelper.setRedisTemplate(template);
		return RedisHelper.INSTANCE;
	}

	/**
	 * 缓存注解操作切面</br>
	 * 必须在 redisHelper 初始化之后使用
	 * @param stringRedisTemplate 字符串存储的Redis操作类
	 * @param cacheSerializer 缓存序列化器
	 * @return CacheStringAspect 缓存注解操作切面
	 */
	@Bean
	@DependsOn("redisHelper")
	@ConditionalOnMissingBean
	public CacheStringAspect cacheStringAspect(StringRedisTemplate stringRedisTemplate,
											   CacheSerializer cacheSerializer) {
		return new CacheStringAspect(stringRedisTemplate, cacheSerializer);
	}

}

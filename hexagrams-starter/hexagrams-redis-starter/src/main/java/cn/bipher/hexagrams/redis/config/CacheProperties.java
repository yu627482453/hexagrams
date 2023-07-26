package cn.bipher.hexagrams.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@Data
@ConfigurationProperties(prefix = CacheProperties.PREFIX)
public class CacheProperties {

	public static final String PREFIX = "hexagrams.redis";

	/**
	 * 通用的key前缀
	 */
	private String keyPrefix = "";

	/**
	 * redis锁 后缀
	 */
	private String lockKeySuffix = "locked";

	/**
	 * 默认分隔符
	 */
	private String delimiter = ":";

	/**
	 * 空值标识
	 */
	private String nullValue = "N_V";

	/**
	 * 默认缓存数据的超时时间(s)
	 */
	private long expireTime = 86400L;

	/**
	 * 默认锁的超时时间(s)
	 */
	private long defaultLockTimeout = 10L;

	@NestedConfigurationProperty
	private KeyEventConfig keyExpiredEvent = new KeyEventConfig();

	@NestedConfigurationProperty
	private KeyEventConfig keyDeletedEvent = new KeyEventConfig();

	@NestedConfigurationProperty
	private KeyEventConfig keySetEvent = new KeyEventConfig();

}

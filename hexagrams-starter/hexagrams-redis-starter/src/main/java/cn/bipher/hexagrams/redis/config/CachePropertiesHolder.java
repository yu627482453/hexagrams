package cn.bipher.hexagrams.redis.config;

/**
 * 缓存配置持有者，方便静态获取配置信息
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public final class CachePropertiesHolder {

	private static final CachePropertiesHolder INSTANCE = new CachePropertiesHolder();

	private CacheProperties cacheProperties;

	public void setCacheProperties(CacheProperties cacheProperties) {
		INSTANCE.cacheProperties = cacheProperties;
	}

	private static CacheProperties cacheProperties() {
		return INSTANCE.cacheProperties;
	}

	public static String keyPrefix() {
		return cacheProperties().getKeyPrefix();
	}

	public static String lockKeySuffix() {
		return cacheProperties().getLockKeySuffix();
	}

	public static String delimiter() {
		return cacheProperties().getDelimiter();
	}

	public static String nullValue() {
		return cacheProperties().getNullValue();
	}

	public static long expireTime() {
		return cacheProperties().getExpireTime();
	}

	public static long defaultLockTimeout() {
		return cacheProperties().getDefaultLockTimeout();
	}

}

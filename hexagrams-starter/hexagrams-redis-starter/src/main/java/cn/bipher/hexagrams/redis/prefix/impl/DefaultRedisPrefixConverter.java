package cn.bipher.hexagrams.redis.prefix.impl;


import cn.bipher.hexagrams.redis.prefix.IRedisPrefixConverter;

/**
 * redis key前缀默认转换器
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public class DefaultRedisPrefixConverter implements IRedisPrefixConverter {

	private final String prefix;

	public DefaultRedisPrefixConverter(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public boolean match() {
		return true;
	}

}

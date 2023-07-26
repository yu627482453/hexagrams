package cn.bipher.hexagrams.redis.serialize;

import cn.bipher.hexagrams.redis.prefix.IRedisPrefixConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;

/**
 * 自定义Key序列化工具，添加全局key前缀
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@Slf4j
public class PrefixStringRedisSerializer extends StringRedisSerializer {

	private final IRedisPrefixConverter iRedisPrefixConverter;

	public PrefixStringRedisSerializer(IRedisPrefixConverter iRedisPrefixConverter) {
		super(StandardCharsets.UTF_8);
		this.iRedisPrefixConverter = iRedisPrefixConverter;
	}

	@Override
	public String deserialize(byte[] bytes) {
		byte[] unwrap = iRedisPrefixConverter.unwrap(bytes);
		return super.deserialize(unwrap);
	}

	@Override
	public byte[] serialize(String key) {
		byte[] originBytes = super.serialize(key);
		return iRedisPrefixConverter.wrap(originBytes);
	}

}

package cn.bipher.hexagrams.redis.keyevent.template;

import org.springframework.lang.Nullable;

/**
 * common key event message template
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public interface KeyEventMessageTemplate {

	/**
	 * handle actual message with notified key
	 * @param key notified key from redis server
	 */
	void handleMessage(@Nullable String key);

	/**
	 * support this template or not
	 * @param key notified key from redis server
	 * @return {@code true} is support and {@code false} is not support
	 */
	boolean support(@Nullable String key);

}

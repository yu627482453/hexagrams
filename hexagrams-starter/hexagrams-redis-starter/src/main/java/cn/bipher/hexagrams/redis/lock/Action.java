package cn.bipher.hexagrams.redis.lock;


import cn.bipher.hexagrams.redis.config.CachePropertiesHolder;
import cn.bipher.hexagrams.redis.lock.function.ThrowingExecutor;

import java.util.concurrent.TimeUnit;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 * @param <T> 返回类型
 */
@FunctionalInterface
public interface Action<T> {

	/**
	 * 执行方法,采用配置文件中的默认过期时间
	 * @param lockKey 待锁定的 key
	 * @param supplier 执行方法
	 * @return 状态处理器
	 */
	default StateHandler<T> action(String lockKey, ThrowingExecutor<T> supplier) {
		return action(lockKey, CachePropertiesHolder.defaultLockTimeout(), TimeUnit.SECONDS, supplier);
	}

	/**
	 * 执行方法
	 * @param lockKey 待锁定的 key
	 * @param timeout 锁定过期时间,默认单位秒
	 * @param supplier 执行方法
	 * @return 状态处理器
	 */
	default StateHandler<T> action(String lockKey, long timeout, ThrowingExecutor<T> supplier) {
		return action(lockKey, timeout, TimeUnit.SECONDS, supplier);
	}

	/**
	 * 执行方法
	 * @param lockKey 待锁定的 key
	 * @param timeout 锁定过期时间
	 * @param timeUnit 锁定过期时间单位
	 * @param supplier 执行方法
	 * @return 状态处理器
	 */
	StateHandler<T> action(String lockKey, long timeout, TimeUnit timeUnit, ThrowingExecutor<T> supplier);

}

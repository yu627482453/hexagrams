package cn.bipher.hexagrams.redis.operation;

import cn.bipher.hexagrams.redis.config.CachePropertiesHolder;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public abstract class AbstractCacheOps {

	protected AbstractCacheOps(ProceedingJoinPoint joinPoint) {
		this.joinPoint = joinPoint;
	}

	private final ProceedingJoinPoint joinPoint;

	/**
	 * 织入方法
	 * @return ProceedingJoinPoint
	 */
	public ProceedingJoinPoint joinPoint() {
		return joinPoint;
	}

	/**
	 * 检查缓存数据是否是空值
	 * @param cacheData 缓存数据
	 * @return true: 是空值
	 */
	public boolean nullValue(Object cacheData) {
		return CachePropertiesHolder.nullValue().equals(cacheData);
	}

}

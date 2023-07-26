package cn.bipher.hexagrams.redis.operation;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Type;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public class CachedOps extends AbstractCacheOps {

	/**
	 * 数据类型
	 */
	private final Type returnType;

	/**
	 * 缓存分布式锁的key
	 */
	private final String lockKey;

	/**
	 * 从Redis中获取缓存数据的操作
	 */
	private final Supplier<String> cacheQuery;

	/**
	 * 向缓存写入数据
	 */
	private final Consumer<Object> cachePut;

	/**
	 * 基本构造函数
	 * @param joinPoint 织入方法
	 * @param lockKey 分布式锁key
	 * @param cacheQuery 查询缓存函数
	 * @param cachePut 更新缓存函数
	 * @param returnType 返回数据类型
	 */
	public CachedOps(ProceedingJoinPoint joinPoint, String lockKey, Supplier<String> cacheQuery,
			Consumer<Object> cachePut, Type returnType) {
		super(joinPoint);
		this.lockKey = lockKey;
		this.cacheQuery = cacheQuery;
		this.cachePut = cachePut;
		this.returnType = returnType;
	}

	public Supplier<String> cacheQuery() {
		return cacheQuery;
	}

	public Consumer<Object> cachePut() {
		return cachePut;
	}

	public Type returnType() {
		return returnType;
	}

	public String lockKey() {
		return lockKey;
	}

}

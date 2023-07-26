package cn.bipher.hexagrams.redis.operation;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.function.Consumer;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public class CachePutOps extends AbstractCacheOps {

	/**
	 * 向缓存写入数据
	 */
	private final Consumer<Object> cachePut;

	public CachePutOps(ProceedingJoinPoint joinPoint, Consumer<Object> cachePut) {
		super(joinPoint);
		this.cachePut = cachePut;
	}

	public Consumer<Object> cachePut() {
		return cachePut;
	}

}

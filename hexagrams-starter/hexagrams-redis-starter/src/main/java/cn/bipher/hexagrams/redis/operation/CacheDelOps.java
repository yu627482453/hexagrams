package cn.bipher.hexagrams.redis.operation;

import cn.bipher.hexagrams.redis.operation.function.VoidMethod;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public class CacheDelOps extends AbstractCacheOps {

	/**
	 * 删除缓存数据
	 */
	private final VoidMethod cacheDel;

	public CacheDelOps(ProceedingJoinPoint joinPoint, VoidMethod cacheDel) {
		super(joinPoint);
		this.cacheDel = cacheDel;
	}

	public VoidMethod cacheDel() {
		return cacheDel;
	}

}

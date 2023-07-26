package cn.bipher.hexagrams.redis.operation;

import cn.bipher.hexagrams.redis.operation.function.VoidMethod;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public class CacheDelsOps extends AbstractCacheOps {

	/**
	 * 删除缓存数据
	 */
	private final VoidMethod[] cacheDels;

	public CacheDelsOps(ProceedingJoinPoint joinPoint, VoidMethod[] cacheDels) {
		super(joinPoint);
		this.cacheDels = cacheDels;
	}

	public VoidMethod[] cacheDel() {
		return cacheDels;
	}

}

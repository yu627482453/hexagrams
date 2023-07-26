package cn.bipher.hexagrams.redis.lock.function;

/**
 * 允许抛出异常的执行器
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
public interface ThrowingExecutor<T> {

	/**
	 * 可抛异常的supplier
	 * @return T
	 * @throws Throwable 异常
	 */
	@SuppressWarnings("java:S112")
	T execute() throws Throwable;

}

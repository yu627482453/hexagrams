package cn.bipher.hexagrams.redis.lock.function;

/**
 * 异常处理器，可在处理完异常后再次抛出异常
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@FunctionalInterface
public interface ExceptionHandler {

	/**
	 * 处理异常
	 * @param throwable 待处理的异常
	 */
	void handle(Throwable throwable);

}

package cn.bipher.hexagrams.redis.operation.function;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@FunctionalInterface
public interface VoidMethod {

	/**
	 * 只执行 无返回值
	 */
	void run();

}

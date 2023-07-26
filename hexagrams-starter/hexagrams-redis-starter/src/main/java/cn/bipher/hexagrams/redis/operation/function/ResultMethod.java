package cn.bipher.hexagrams.redis.operation.function;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@FunctionalInterface
public interface ResultMethod<T> {

	/**
	 * 执行并返回一个结果
	 * @return result
	 */
	T run();

}

package cn.bipher.hexagrams.log.operation.annotation;

import java.lang.annotation.*;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogging {

	/**
	 * 日志信息
	 * @return 日志描述信息
	 */
	String msg() default "";

	/**
	 * 日志操作类型
	 * @return 日志操作类型枚举
	 */
	int type();

	/**
	 * 是否保存方法入参
	 * @return boolean
	 */
	boolean recordParams() default true;

	/**
	 * 是否保存方法返回值
	 * @return boolean
	 */
	boolean recordResult() default true;

}

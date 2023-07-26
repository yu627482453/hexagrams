package cn.bipher.hexagrams.log.operation.annotation;

import cn.bipher.hexagrams.log.operation.enums.OperationTypes;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@OperationLogging(type = OperationTypes.UPDATE)
public @interface UpdateOperationLogging {

	/**
	 * 日志信息
	 * @return 日志描述信息
	 */
	@AliasFor(annotation = OperationLogging.class)
	String msg();

	/**
	 * 是否保存方法入参
	 * @return boolean
	 */
	@AliasFor(annotation = OperationLogging.class)
	boolean recordParams() default true;

	/**
	 * 是否保存方法返回值
	 * @return boolean
	 */
	@AliasFor(annotation = OperationLogging.class)
	boolean recordResult() default true;

}

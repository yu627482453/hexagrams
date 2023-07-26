package cn.bipher.hexagrams.log.operation.aspect;

import cn.bipher.hexagrams.log.operation.annotation.OperationLogging;
import cn.bipher.hexagrams.log.operation.handler.OperationLogHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * 操作日志切面类
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class OperationLogAspect<T> {

	private final OperationLogHandler<T> operationLogHandler;

	@Around("execution(@(@cn.bipher.hexagrams.log.operation.annotation.OperationLogging *) * *(..)) "
			+ "|| @annotation(cn.bipher.hexagrams.log.operation.annotation.OperationLogging)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		// 开始时间
		long startTime = System.currentTimeMillis();

		// 获取目标方法
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		// 获取操作日志注解 备注： AnnotationUtils.findAnnotation 方法无法获取继承的属性
		OperationLogging operationLogging = AnnotatedElementUtils.findMergedAnnotation(method, OperationLogging.class);

		// 获取操作日志 DTO
		Assert.notNull(operationLogging, "operationLogging annotation must not be null!");

		T operationLog = operationLogHandler.buildLog(operationLogging, joinPoint);

		Throwable throwable = null;
		Object result = null;
		try {
			result = joinPoint.proceed();
			return result;
		}
		catch (Throwable e) {
			throwable = e;
			throw e;
		}
		finally {
			// 是否保存响应内容
			boolean isSaveResult = operationLogging.recordResult();
			// 操作日志记录处理
			handleLog(joinPoint, startTime, operationLog, throwable, isSaveResult, result);
		}
	}

	private void handleLog(ProceedingJoinPoint joinPoint, long startTime, T operationLog, Throwable throwable,
			boolean isSaveResult, Object result) {
		try {
			// 结束时间
			long executionTime = System.currentTimeMillis() - startTime;
			// 记录执行信息
			operationLogHandler.recordExecutionInfo(operationLog, joinPoint, executionTime, throwable, isSaveResult,
					result);
			// 处理操作日志
			operationLogHandler.handleLog(operationLog);
		}
		catch (Exception e) {
			log.error("记录操作日志异常：{}", operationLog);
		}
	}

}

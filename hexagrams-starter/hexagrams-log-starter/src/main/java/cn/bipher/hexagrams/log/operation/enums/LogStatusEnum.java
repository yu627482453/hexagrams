package cn.bipher.hexagrams.log.operation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作状态枚举
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/26 10:54
 */
@Getter
@AllArgsConstructor
public enum LogStatusEnum {

	/**
	 * 成功
	 */
	SUCCESS(1),
	/**
	 * 失败
	 */
	FAIL(0);

	private final int value;

}

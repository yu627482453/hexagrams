package cn.bipher.hexagrams.datasource.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据源配置类型
 * @author Bipher
 * @version 1
 * @date 2022/12/30 13:46
 */
@Getter
@AllArgsConstructor
public enum DsConfTypeEnum {

	/**
	 * 主机链接
	 */
	HOST(0, "主机链接"),

	/**
	 * JDBC链接
	 */
	JDBC(1, "JDBC链接");

	private final Integer type;

	private final String description;

}

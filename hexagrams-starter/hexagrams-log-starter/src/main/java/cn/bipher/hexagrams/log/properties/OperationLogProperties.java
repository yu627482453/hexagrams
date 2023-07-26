package cn.bipher.hexagrams.log.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 操作日志配置
 *
 * @author hccake
 */
@Data
@ConfigurationProperties(prefix = OperationLogProperties.PREFIX)
public class OperationLogProperties {

	public static final String PREFIX = "hexagrams.log.operation";

	/**
	 * 开启操作日志的记录
	 */
	private boolean enabled = true;

}

package cn.bipher.hexagrams.job.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * xxl-job配置
 *
 * @author Bipher
 * @version 1
 * @date 2023/7/25 23:05
 */
@Data
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobProperties {

	@NestedConfigurationProperty
	private XxlAdminProperties admin = new XxlAdminProperties();

	@NestedConfigurationProperty
	private XxlExecutorProperties executor = new XxlExecutorProperties();

}

package cn.bipher.hexagrams.log;

import cn.bipher.hexagrams.log.access.filter.AccessLogFilter;
import cn.bipher.hexagrams.log.access.handler.AccessLogHandler;
import cn.bipher.hexagrams.log.properties.AccessLogProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author Hccake 2019/10/15 18:20
 */
@Slf4j
@ConditionalOnWebApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(AccessLogProperties.class)
@ConditionalOnProperty(prefix = AccessLogProperties.PREFIX, name = "enabled", matchIfMissing = true,
		havingValue = "true")
public class AccessLogAutoConfiguration {

	private final AccessLogHandler<?> accessLogService;

	private final AccessLogProperties accessLogProperties;

	@Bean
	@ConditionalOnClass(AccessLogHandler.class)
	public FilterRegistrationBean<AccessLogFilter> accessLogFilterRegistrationBean() {
		log.debug("access log 记录拦截器已开启====");
		FilterRegistrationBean<AccessLogFilter> registrationBean = new FilterRegistrationBean<>(
				new AccessLogFilter(accessLogService, accessLogProperties.getIgnoreUrlPatterns()));
		registrationBean.setOrder(accessLogProperties.getFilterOrder());
		return registrationBean;
	}

}

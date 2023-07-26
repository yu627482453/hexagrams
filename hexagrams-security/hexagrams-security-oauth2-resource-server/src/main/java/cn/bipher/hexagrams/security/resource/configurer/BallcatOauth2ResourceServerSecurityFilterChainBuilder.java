package cn.bipher.hexagrams.security.resource.configurer;

import cn.bipher.hexagrams.security.resource.properties.OAuth2ResourceServerProperties;
import cn.hutool.core.util.ArrayUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.List;

/**
 * 资源服务器的配置
 *
 * @author hccake
 */
@RequiredArgsConstructor
public class BallcatOauth2ResourceServerSecurityFilterChainBuilder
		implements Oauth2ResourceServerSecurityFilterChainBuilder {

	private final OAuth2ResourceServerProperties oAuth2ResourceServerProperties;

	private final AuthenticationEntryPoint authenticationEntryPoint;

	private final BearerTokenResolver bearerTokenResolver;

	private final ObjectProvider<List<OAuth2ResourceServerConfigurerCustomizer>> configurerCustomizersProvider;

	private final ObjectProvider<List<OAuth2ResourceServerExtensionConfigurer<HttpSecurity>>> extensionConfigurersProvider;

	@Override
	public SecurityFilterChain build(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			// 拦截 url 配置
			.authorizeRequests()
			.antMatchers(ArrayUtil.toArray(oAuth2ResourceServerProperties.getIgnoreUrls(), String.class))
			.permitAll()
			.anyRequest().authenticated()

			// 关闭 csrf 跨站攻击防护
			.and().csrf().disable()

			// 开启 OAuth2 资源服务
			.oauth2ResourceServer().authenticationEntryPoint(authenticationEntryPoint)
			// bearToken 解析器
			.bearerTokenResolver(bearerTokenResolver)
			// 不透明令牌，
			.opaqueToken();
		// @formatter:on

		// 允许嵌入iframe
		if (!oAuth2ResourceServerProperties.isIframeDeny()) {
			http.headers().frameOptions().disable();
		}

		// 自定义处理
		List<OAuth2ResourceServerConfigurerCustomizer> configurerCustomizers = configurerCustomizersProvider
			.getIfAvailable(Collections::emptyList);
		for (OAuth2ResourceServerConfigurerCustomizer configurerCustomizer : configurerCustomizers) {
			configurerCustomizer.customize(http);
		}

		// 扩展配置
		List<OAuth2ResourceServerExtensionConfigurer<HttpSecurity>> extensionConfigurers = extensionConfigurersProvider
			.getIfAvailable(Collections::emptyList);
		for (OAuth2ResourceServerExtensionConfigurer<HttpSecurity> extensionConfigurer : extensionConfigurers) {
			http.apply(extensionConfigurer);
		}

		return http.build();
	}

}
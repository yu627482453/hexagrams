package cn.bipher.security.oauth2.server.authorization.autoconfigure;

import cn.bipher.hexagrams.common.security.authentication.OAuth2UserAuthenticationToken;
import cn.bipher.hexagrams.common.security.jackson2.LongMixin;
import cn.bipher.hexagrams.common.security.jackson2.OAuth2UserAuthenticationTokenMixin;
import cn.bipher.hexagrams.common.security.jackson2.UserMixin;
import cn.bipher.hexagrams.common.security.userdetails.User;
import cn.bipher.hexagrams.common.security.util.PasswordUtils;
import cn.bipher.security.oauth2.server.authorization.OAuth2AuthorizationObjectMapperCustomizer;
import cn.bipher.security.oauth2.server.authorization.config.OAuth2AuthorizationServerSecurityFilterChainBuilder;
import cn.bipher.security.oauth2.server.authorization.config.configurer.OAuth2AuthorizationServerExtensionConfigurer;
import cn.bipher.security.oauth2.server.authorization.config.customizer.OAuth2AuthorizationServerConfigurerCustomizer;
import cn.bipher.security.oauth2.server.authorization.properties.OAuth2AuthorizationServerProperties;
import cn.bipher.security.oauth2.server.authorization.token.BallcatOAuth2TokenCustomizer;
import cn.bipher.security.oauth2.server.authorization.web.authentication.OAuth2TokenRevocationResponseHandler;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * OAuth2授权服务器自动配置类
 *
 * @author Hccake
 */
@Import({ OAuth2AuthorizationServerConfigurerCustomizerConfiguration.class,
		OAuth2AuthorizationServerExtensionConfigurerConfiguration.class })
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
@EnableConfigurationProperties(OAuth2AuthorizationServerProperties.class)
public class OAuth2AuthorizationServerAutoConfiguration {

	public static final String OAUTH2_AUTHORIZATION_SERVER_SECURITY_FILTER_CHAIN_BEAN_NAME = "oauth2AuthorizationServerSecurityFilterChain";

	/**
	 * OAuth2AuthorizationServerConfigurer 的适配器
	 * @param oAuth2AuthorizationServerConfigurerCustomizers
	 * OAuth2AuthorizationServerConfigurer 的定制器列表
	 * @param oAuth2AuthorizationServerExtensionConfigurers
	 * oAuth2AuthorizationServerExtensionConfigurer 的配置扩展器列表
	 * @return OAuth2AuthorizationServerConfigurerAdapter
	 */
	@Bean
	@ConditionalOnMissingBean(name = OAUTH2_AUTHORIZATION_SERVER_SECURITY_FILTER_CHAIN_BEAN_NAME,
			value = OAuth2AuthorizationServerSecurityFilterChainBuilder.class)
	public OAuth2AuthorizationServerSecurityFilterChainBuilder oAuth2AuthorizationServerSecurityFilterChainBuilder(
			List<OAuth2AuthorizationServerConfigurerCustomizer> oAuth2AuthorizationServerConfigurerCustomizers,
			List<OAuth2AuthorizationServerExtensionConfigurer<?, HttpSecurity>> oAuth2AuthorizationServerExtensionConfigurers) {
		return new BallcatOAuth2AuthorizationServerSecurityFilterChainBuilder(
				oAuth2AuthorizationServerConfigurerCustomizers, oAuth2AuthorizationServerExtensionConfigurers);
	}

	/**
	 * OAuth2 授权服务器的安全过滤器链，如果和资源服务器共存，需要将其放在资源服务器之前
	 */
	@Bean(name = OAUTH2_AUTHORIZATION_SERVER_SECURITY_FILTER_CHAIN_BEAN_NAME)
	@Order(1)
	@ConditionalOnMissingBean(name = OAUTH2_AUTHORIZATION_SERVER_SECURITY_FILTER_CHAIN_BEAN_NAME)
	public SecurityFilterChain oauth2AuthorizationServerSecurityFilterChain(
			OAuth2AuthorizationServerSecurityFilterChainBuilder builder, HttpSecurity httpSecurity) throws Exception {
		return builder.build(httpSecurity);
	}

	/**
	 * OAuth2 授权服务器中注册的 client 仓库
	 */
	@Bean
	@ConditionalOnMissingBean
	public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
		return new JdbcRegisteredClientRepository(jdbcTemplate);
	}

	/**
	 * OAuth2 授权管理Service
	 */
	@Bean
	@ConditionalOnMissingBean
	public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
			RegisteredClientRepository registeredClientRepository,
			ObjectProvider<OAuth2AuthorizationObjectMapperCustomizer> objectMapperCustomizerObjectProvider) {
		JdbcOAuth2AuthorizationService oAuth2AuthorizationService = new JdbcOAuth2AuthorizationService(jdbcTemplate,
				registeredClientRepository);

		// 需要注册自己的 mixin 来处理类型转换
		// link
		// https://github.com/spring-projects/spring-authorization-server/issues/397#issuecomment-900148920
		JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper rowMapper = new JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper(
				registeredClientRepository);

		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = JdbcOAuth2AuthorizationService.class.getClassLoader();
		List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
		objectMapper.registerModules(securityModules);
		objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());

		// You will need to write the Mixin for your class so Jackson can marshall it.
		objectMapper.addMixIn(Long.class, LongMixin.class);
		objectMapper.addMixIn(User.class, UserMixin.class);
		objectMapper.addMixIn(OAuth2UserAuthenticationToken.class, OAuth2UserAuthenticationTokenMixin.class);

		// 定制 objectMapper
		objectMapperCustomizerObjectProvider.ifAvailable(customizer -> customizer.customize(objectMapper));

		rowMapper.setObjectMapper(objectMapper);

		oAuth2AuthorizationService.setAuthorizationRowMapper(rowMapper);

		return oAuth2AuthorizationService;
	}

	/**
	 * OAuth2AuthorizationConsentService
	 */
	@Bean
	@ConditionalOnMissingBean
	public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate,
			RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
	}

	/**
	 * 授权服务器基本端点地址配置
	 * @return AuthorizationServerSettings
	 */
	@Bean
	@ConditionalOnMissingBean
	public AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder().build();
	}

	/**
	 * OAuth2 Token 撤销响应处理器
	 * @param publisher 事件发布器
	 * @return OAuth2TokenRevocationResponseHandler
	 */
	@Bean
	@ConditionalOnMissingBean
	public OAuth2TokenRevocationResponseHandler oAuth2TokenRevocationResponseHandler(
			ApplicationEventPublisher publisher) {
		return new OAuth2TokenRevocationResponseHandler(publisher);
	}

	/**
	 * 密码管理器
	 */
	@Bean
	@ConditionalOnMissingBean
	public PasswordEncoder passwordEncoder() {
		return PasswordUtils.createDelegatingPasswordEncoder();
	}

	/**
	 * 对于使用不透明令牌的 client，需要存储对应的用户信息，以便在后续的请求中获取用户信息
	 */
	@Bean
	@ConditionalOnMissingBean(OAuth2TokenCustomizer.class)
	public OAuth2TokenCustomizer<OAuth2TokenClaimsContext> oAuth2TokenCustomizer() {
		return new BallcatOAuth2TokenCustomizer();
	}

}

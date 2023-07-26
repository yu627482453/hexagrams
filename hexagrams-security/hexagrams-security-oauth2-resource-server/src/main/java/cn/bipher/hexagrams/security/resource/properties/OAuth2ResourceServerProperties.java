package cn.bipher.hexagrams.security.resource.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源服务器的配置文件，用于配置 token 鉴定方式。由于目前 ballcat 授权服务器使用 不透明令牌，所以这里也暂时不做 jwt令牌支持的扩展
 *
 * @see org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties
 * @author hccake
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "hexagrams.security.oauth2.resourceserver")
public class OAuth2ResourceServerProperties {

	/**
	 * 忽略鉴权的 url 列表
	 */
	private List<String> ignoreUrls = new ArrayList<>();

	/**
	 * 是否禁止嵌入iframe
	 */
	private boolean iframeDeny = true;

	/**
	 * 共享存储的token，这种情况下，利用 tokenStore 可以直接获取 token 信息
	 */
	private boolean sharedStoredToken = true;

	/**
	 * 当 sharedStoredToken 为 false 时生效。 主要用于配置远程端点
	 */
	private final Opaquetoken opaqueToken = new Opaquetoken();

	@Getter
	@Setter
	public static class Opaquetoken {

		/**
		 * Client id used to authenticate with the token introspection endpoint.
		 */
		private String clientId;

		/**
		 * Client secret used to authenticate with the token introspection endpoint.
		 */
		private String clientSecret;

		/**
		 * OAuth 2.0 endpoint through which token introspection is accomplished.
		 */
		private String introspectionUri;

	}

}

package org.weicong.uas.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.weicong.common.auth.config.CornAccessDeniedHandler;
import org.weicong.common.auth.config.CornTokenEnhancer;
import org.weicong.common.auth.exception.CornWebResponseExceptionTranslator;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

/**
 * @description corn授权服务器
 * @author weicong
 * @date 2019年8月21日
 * @version 1.0
 */
@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final ObjectMapper objectMapper;
	private final AuthenticationManager authenticationManager;
	private final RedisConnectionFactory redisConnectionFactory;
	private final CornUserDetailsService cornUserDetailsService;
	private final Environment env;
	private final PasswordEncoder encoder;
	
	private final static String ENV_CLIENT_PREFIX = "security.oauth2.client.";	
	private final static String ENV_RESOURCE_ID = "security.oauth2.resource.id";	
	private static String CLIENT_ID1 = ENV_CLIENT_PREFIX + "client-id1";	
	private static String CLIENT_SECRET1 = ENV_CLIENT_PREFIX + "client-secret1";	
	private static String CLIENT_ID2 = ENV_CLIENT_PREFIX + "client-id2";	
	private static String CLIENT_SECRET2 = ENV_CLIENT_PREFIX + "client-secret2";
	private static String SCOPES = ENV_CLIENT_PREFIX + "scopes";
	private static String AUTHORITIES = ENV_CLIENT_PREFIX + "authorities";

	@SuppressWarnings("unchecked")
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// @formatter:off
		TokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
		endpoints
			.tokenStore(new RedisTokenStore(redisConnectionFactory))
			.authenticationManager(authenticationManager)
			.userDetailsService(cornUserDetailsService)
			// 允许 GET、POST 请求获取 token，即访问端点：oauth/token
			.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
			.reuseRefreshTokens(true)
			.exceptionTranslator(new CornWebResponseExceptionTranslator())
			.tokenEnhancer(new CornTokenEnhancer());
		// @formatter:on
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// 配置两个客户端,一个用于password认证一个用于client认证
		// @formatter:off
		clients
			.inMemory()
			.withClient(env.getProperty(CLIENT_ID1))
			.secret(encoder.encode(env.getProperty(CLIENT_SECRET1)))// 客户端也要加密
			.authorizedGrantTypes("client_credentials", "refresh_token")
			.scopes(env.getProperty(SCOPES))
			.authorities(env.getProperty(AUTHORITIES))
			.resourceIds(env.getProperty(ENV_RESOURCE_ID))
			.and()
			
			.withClient(env.getProperty(CLIENT_ID2))
			.secret(encoder.encode(env.getProperty(CLIENT_SECRET2)))
			.authorizedGrantTypes("password", "refresh_token")
			.scopes(env.getProperty(SCOPES))
			.authorities(env.getProperty(AUTHORITIES))
			.resourceIds(env.getProperty(ENV_RESOURCE_ID))
			.accessTokenValiditySeconds(30)
			.refreshTokenValiditySeconds(30);
		// @formatter:on
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		// 允许表单认证
		// @formatter:off
		oauthServer
			.allowFormAuthenticationForClients()
			.accessDeniedHandler(new CornAccessDeniedHandler(objectMapper))
			//.addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter)// 无法由spring bean管理
		;
		// @formatter:on
	}
		
	/**
	 * .addTokenEndpointAuthenticationFilter 和 下面的不能共存，否则filter会调用两次
	 * @return
	 */
//	@Bean
//	public IntegrationAuthenticationFilter integrationAuthenticationFilter() {
//		return new IntegrationAuthenticationFilter();
//	}
	
}

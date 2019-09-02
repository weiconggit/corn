package org.weicong.uas.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
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

	private static final String DEMO_RESOURCE_ID = "order";

	private final ObjectMapper objectMapper;
	private final AuthenticationManager authenticationManager;
	private final RedisConnectionFactory redisConnectionFactory;
	private final CornUserDetailsService cornUserDetailsService;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// @formatter:off
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
		// @formatter:off
		// 配置两个客户端,一个用于password认证一个用于client认证
		clients.inMemory()
			.withClient("client_1")
			.resourceIds(DEMO_RESOURCE_ID)
			.authorizedGrantTypes("client_credentials", "refresh_token")
			.scopes("select").authorities("client")
			.secret("123456").and()
			.withClient("client_2")
			.resourceIds(DEMO_RESOURCE_ID)
			.authorizedGrantTypes("password", "refresh_token")
			.scopes("select").authorities("client")
			.secret("123456");
		// @formatter:on
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		// 允许表单认证
		oauthServer.allowFormAuthenticationForClients()
		.accessDeniedHandler(new CornAccessDeniedHandler(objectMapper))
		
		;
	}

	
}

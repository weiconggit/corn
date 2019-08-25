package org.weicong.uas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @description corn授权服务器
 * @author weicong
 * @date 2019年8月21日
 * @version 1.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String DEMO_RESOURCE_ID = "order";

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// 配置两个客户端,一个用于password认证一个用于client认证
		clients.inMemory().withClient("client_1").resourceIds(DEMO_RESOURCE_ID)
				.authorizedGrantTypes("client_credentials", "refresh_token").scopes("select").authorities("client")
				.secret("123456").and().withClient("client_2").resourceIds(DEMO_RESOURCE_ID)
				.authorizedGrantTypes("password", "refresh_token").scopes("select").authorities("client")
				.secret("123456");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				// token 存储在 redis
				.tokenStore(new RedisTokenStore(redisConnectionFactory)).authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService)
				// 2018-4-3 增加配置，允许 GET、POST 请求获取 token，即访问端点：oauth/token
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

		endpoints.reuseRefreshTokens(true);
		endpoints.exceptionTranslator(new CornWebResponseExceptionTranslator());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		// 允许表单认证
		oauthServer.allowFormAuthenticationForClients();
	}

	
}

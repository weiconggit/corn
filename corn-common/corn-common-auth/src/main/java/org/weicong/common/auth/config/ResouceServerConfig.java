package org.weicong.common.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description 资源服务适配器
 * @author weicong
 * @date 2019年8月22日
 * @version 1.0
 */
@Configuration
@EnableResourceServer
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String DEMO_RESOURCE_ID = "order";

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(tokenServices())
				// 资源ID
				.resourceId(DEMO_RESOURCE_ID)
				.authenticationEntryPoint(new CornAuthenticationEntryPoint(objectMapper))
				.accessDeniedHandler(new CornAccessDeniedHandler(objectMapper));
		super.configure(resources);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/alive").permitAll()
		.antMatchers("/**").authenticated()
		;
//		http.csrf().disable();
	}

	/**
	 * @Description OAuth2 token持久化接口
	 * @Date 2019/7/15 18:12
	 * @Version 1.0
	 */
	@Bean
	public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}

	/**
	 * @Description 令牌服务
	 * @Date 2019/7/15 18:07
	 * @Version 1.0
	 */
	@Bean
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}

}

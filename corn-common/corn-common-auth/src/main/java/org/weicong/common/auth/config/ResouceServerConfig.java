package org.weicong.common.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import lombok.AllArgsConstructor;

/**
 * @description 资源服务适配器
 * @author weicong
 * @date 2019年8月22日
 * @version 1.0
 */
@AllArgsConstructor
@Configuration
@EnableResourceServer
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {

	private final RedisConnectionFactory redisConnectionFactory;
	private final InterceptorConfigProperties ignoreURLs;
	private final Environment env;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		// @formatter:off
		resources
			.resourceId(env.getProperty("security.oauth2.resource.id"))// 资源ID
		    .tokenServices(tokenServices())
			.authenticationEntryPoint(new CornAuthenticationEntryPoint())
			.accessDeniedHandler(new CornAccessDeniedHandler())
			;
		// @formatter:on
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		String[] urls = new String[ignoreURLs.getExcludePath().size()];
		ignoreURLs.getExcludePath().toArray(urls);
		http
			.authorizeRequests()
			.antMatchers(urls).permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable();
		// @formatter:on
	}
	
	/**
	 * OAuth2 token 持久化接口，@Bean 是为了AuthUtil能够注入
	 * 
	 * @return
	 */
	@Bean
	public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}

	/**
	 * 令牌服务
	 * 
	 * @return
	 */
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setTokenEnhancer(new CornTokenEnhancer());
		return defaultTokenServices;
	}
	

}

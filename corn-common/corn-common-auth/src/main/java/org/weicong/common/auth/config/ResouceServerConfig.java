package org.weicong.common.auth.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	private static final String DEMO_RESOURCE_ID = "order";

	private final ObjectMapper objectMapper;
	private final RedisConnectionFactory redisConnectionFactory;

	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		// @formatter:off
		resources.resourceId(DEMO_RESOURCE_ID)// 资源ID
			.tokenStore(tokenStore())
		    .tokenServices(tokenServices())
			.authenticationEntryPoint(new CornAuthenticationEntryPoint(objectMapper))
			.accessDeniedHandler(new CornAccessDeniedHandler(objectMapper))
			;
		// @formatter:on
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests().antMatchers(
					"/js/**",
					"/css/**",
					"/img/**",
					"/images/**",
					"/fonts/**",
					"/favicon.ico",
					"/oauth/**",
					"/alive"
					,"/oauth/**"
					).permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable()
			;
		// @formatter:on
		
//		http.headers().frameOptions().disable();
//		ExpressionUrlAuthorizationConfigurer<HttpSecurity>
//			.ExpressionInterceptUrlRegistry registry = http
//			.authorizeRequests();
//		registry.antMatchers("/alive").permitAll();
//		registry.anyRequest().authenticated();
//		.and().csrf().disable();
		
//		http.addFilterBefore(new CornAuthenticationProcessingFilter(), WebAsyncManagerIntegrationFilter.class);
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
		return defaultTokenServices;
	}

	/**
	 * 自定义投票器
	 * 
	 * @return
	 */
	@Deprecated
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = Arrays.asList(
				// new WebExpressionVoter(),
				// new RoleVoter(),
				new CornAccessDecisionVoter(), 
				new AuthenticatedVoter());
		return new UnanimousBased(decisionVoters);
	}
	

}

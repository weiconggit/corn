package org.weicong.uas.auth.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @description
 * @author weicong
 * @date 2019年8月22日
 * @version 1.0
 */
@EnableWebSecurity
//@EnableOAuth2Sso
@Order(90)
@Primary
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	/**
	 * spring security basic config
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
			// 当 WebSecurityConfigurer 和 ResourceServerA 同时存在时，会被 ResourceServer的http配置覆盖
//			.antMatchers(
//			"/oauth/**").permitAll()
			.anyRequest().authenticated()
			;
//			.and().csrf().disable();
//			.requestMatchers()
//			.anyRequest()
//			.and()
//			.authorizeRequests()
//			.antMatchers("/oauth/*")
//			.permitAll();
		// @formatter:on
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}

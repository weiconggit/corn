package org.weicong.common.auth.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.weicong.common.auth.interceptor.URLInterceptor;

/**
 * @description 
 * @author weicong extends WebMvcConfigurationSupport 会导致mvc自动配置失效
 * @date 2019年8月30日
 * @version 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration interceptor = registry.addInterceptor(new URLInterceptor());
		// TODO weicong get data from properties
		// formatter:off
		List<String> list = Arrays.asList( 
				"/admin",
				"/js/**",
				"/css/**",
				"/img/**",
				"/images/**",
				"/fonts/**",
				"/favicon.ico",
				"/oauth/**",
				"/error",
				"/alive"
				,"/oauth/**"
				);
		interceptor.excludePathPatterns(list);
		// formatter:on
	}


	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// @formatter:off
		registry
			.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
			.allowedHeaders("*").maxAge(3600).allowCredentials(true);
		// @formatter:on
	}

	
	
}

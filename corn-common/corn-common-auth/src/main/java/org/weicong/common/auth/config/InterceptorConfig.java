package org.weicong.common.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.weicong.common.auth.interceptor.UriInterceptor;

import lombok.AllArgsConstructor;

/**
 * @description 自定义拦截器，目前主要用户URL拦截动态鉴权
 * @author weicong extends WebMvcConfigurationSupport 会导致mvc自动配置失效
 * @date 2019年8月30日
 * @version 1.0
 */
@AllArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	private final InterceptorConfigProperties ignoreUris;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration interceptor = registry.addInterceptor(new UriInterceptor());
		interceptor.excludePathPatterns(ignoreUris.getExcludePath());
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

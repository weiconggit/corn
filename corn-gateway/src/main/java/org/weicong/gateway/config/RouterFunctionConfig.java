package org.weicong.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.weicong.gateway.handler.HystrixFallbackHandler;

import lombok.AllArgsConstructor;

/**
 * @description 熔断降级路由器
 * @author weicong
 * @date 2019年6月5日
 * @version 1.0
 */
@AllArgsConstructor
@Configuration
public class RouterFunctionConfig {

	private HystrixFallbackHandler hystrixFallbackHandler;

	@Bean
	public RouterFunction<?> routerFunction() {
		return RouterFunctions.route(
				RequestPredicates.path("/fallback").and(RequestPredicates
						.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler);
	}
}

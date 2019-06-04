package org.weicong.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

/**
 * @description redis根据ip限流
 * @author weicong
 * @date 2019年6月3日 下午18:20:47
 * @version 1.0
 */
@Configuration
public class LimiterConfig {

	@Bean(value = "ipKeyResolver")
	public KeyResolver ipKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}
}

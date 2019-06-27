package org.weicong.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

/**
 * @description 使用 redis 根据 ip 限流
 * @author weicong
 * @date 2019年6月3日
 * @version 1.0
 */
@Configuration
public class LimiterConfig {

	@Bean(value = "ipKeyResolver")
	public KeyResolver ipKeyResolver() {
	    return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}
}

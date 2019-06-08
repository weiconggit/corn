package org.weicong.gateway.handler;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @description 熔断降级处理器
 * @author weicong
 * @date 2019年6月3日 下午6:25:12
 * @version 1.0
 */
@Slf4j
@Component
public class HystrixFallbackHandler implements HandlerFunction<ServerResponse>{@Override
	
	public Mono<ServerResponse> handle(ServerRequest request) {
		Optional<Object> urls = request.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
		urls.ifPresent(url -> log.error("网关执行请求:[{}]失败, hystrix 执行降级处理", url));		
		return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject("服务异常"));
	}

	
}

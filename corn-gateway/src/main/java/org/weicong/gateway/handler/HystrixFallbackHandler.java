package org.weicong.gateway.handler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author weicong
 * @date 2019年6月3日 下午18:25:12
 * @version 1.0
 */
@RestController
@RequestMapping
public class HystrixFallbackHandler {

	@GetMapping("/fallback")
	public String fallback() {
		return "服务异常";
	}
}

package org.weicong.common.auth.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @description 
 * @author weicong
 * @date 2019年9月20日
 * @version 1.0
 */
@Data
@Configuration
@ConditionalOnExpression("!'${corn.interceptor}'.isEmpty()")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "corn.interceptor")
public class InterceptorConfigProperties {
	
	private List<String> excludePath = new ArrayList<>();

}

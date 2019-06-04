package org.weicong.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description 
 * @author weicong
 * @date 2019年6月3日 下午18:09:19
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CornGatewayApp {

	public static void main(String[] args) {
		SpringApplication.run(CornGatewayApp.class, args);
	}
	
}

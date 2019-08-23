package org.weicong.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description 
 * @author weicong
 * @date 2019年8月15日
 * @version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CornAuthApp {

	public static void main(String[] args) {
		SpringApplication.run(CornAuthApp.class, args);
	}
	
}

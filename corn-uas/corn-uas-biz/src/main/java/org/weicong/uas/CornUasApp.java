package org.weicong.uas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description 
 * @author weicong
 * @time   2019年6月8日
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CornUasApp {

	public static void main(String[] args) {
		SpringApplication.run(CornUasApp.class, args);
	}
	
}

package org.weicong.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description 服务中心
 * @author weicong
 * @date 2019年5月29日
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class CornEurekaApp {

	public static void main(String[] args) {
		SpringApplication.run(CornEurekaApp.class, args);
	}
}

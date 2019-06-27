package org.weicong.common.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * @description
 * @author weicong
 * @date 2019年6月11日
 * @version 1.0
 */
@EnableTransactionManagement
@Configuration
@MapperScan("org.weicong.*.*.mapper")
public class MybatisPlusConfig {

	/**
	 * 分页插件
	 * @return
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
	
}

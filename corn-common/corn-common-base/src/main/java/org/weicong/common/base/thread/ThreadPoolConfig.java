package org.weicong.common.base.thread;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @description 
 * @author weicong
 * @date 2019年6月10日
 * @version 1.0
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig implements AsyncConfigurer {

	@Bean(name = "asyncExecutor")
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();  
		// 线程池所使用的缓冲队列  
		poolTaskExecutor.setQueueCapacity(200);  
		// 线程池维护线程的最少数量  
		poolTaskExecutor.setCorePoolSize(6);  
		// 线程池维护线程的最大数量  
		poolTaskExecutor.setMaxPoolSize(600); 
		// 线程池维护线程所允许的空闲时间  
		poolTaskExecutor.setKeepAliveSeconds(30000);  
		// 线程初始化
		poolTaskExecutor.initialize();
        return poolTaskExecutor;
    }
}
package com.app.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	
	@Bean(name = "taskExecuter")
	public Executor taskExecuter() {
		ThreadPoolTaskExecutor executer=new ThreadPoolTaskExecutor();
		executer.setMaxPoolSize(2);
		executer.setCorePoolSize(2);
		executer.setQueueCapacity(100);
		executer.setThreadNamePrefix("employeethread");
		executer.initialize();
		return executer;
	}
}

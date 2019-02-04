package com.qikserve.hackdazespringdata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qikserve.hackdazespringdata.repository.listener.OrderEventHandler;

@Configuration
public class RepositoryConfiguration {

	@Bean
	OrderEventHandler orderEventHandler() {
		return new OrderEventHandler();
	}
}

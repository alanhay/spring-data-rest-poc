package com.qikserve.hackdazespringdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qikserve.hackdazespringdata.domain.Customer;
import com.qikserve.hackdazespringdata.domain.Order;
import com.qikserve.hackdazespringdata.mixin.CustomerMixin;
import com.qikserve.hackdazespringdata.mixin.OrderMixin;
import com.qikserve.hackdazespringdata.repository.listener.OrderEventHandler;

@Configuration
public class RepositoryConfiguration {

	@Autowired
	private Jackson2ObjectMapperBuilder objectMapperBuilder;

	@Bean
	public ObjectMapper objectMapper() {
		objectMapperBuilder.mixIn(Customer.class, CustomerMixin.class);
		objectMapperBuilder.mixIn(Order.class, OrderMixin.class);

		return objectMapperBuilder.build();
	}

	@Bean
	OrderEventHandler orderEventHandler() {
		return new OrderEventHandler();
	}
}

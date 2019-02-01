package com.qikserve.hackdazespringdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.qikserve.hackdazespringdata.domain.BaseEntity;
import com.qikserve.hackdazespringdata.domain.Customer;
import com.qikserve.hackdazespringdata.domain.Order;
import com.qikserve.hackdazespringdata.domain.controller.mixin.BaseMixin;
import com.qikserve.hackdazespringdata.domain.controller.mixin.CustomerMixin;
import com.qikserve.hackdazespringdata.domain.controller.mixin.OrderMixin;

@SpringBootApplication
public class HackDazeSpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackDazeSpringDataApplication.class, args);
	}

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.mixIn(BaseEntity.class, BaseMixin.class);
		builder.mixIn(Customer.class, CustomerMixin.class);
		builder.mixIn(Order.class, OrderMixin.class);

		return builder;
	}
}

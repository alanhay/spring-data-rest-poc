package uk.co.certait.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.certait.poc.domain.Customer;
import uk.co.certait.poc.domain.Order;
import uk.co.certait.poc.mixin.CustomerMixin;
import uk.co.certait.poc.mixin.OrderMixin;
import uk.co.certait.poc.repository.listener.OrderEventHandler;

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

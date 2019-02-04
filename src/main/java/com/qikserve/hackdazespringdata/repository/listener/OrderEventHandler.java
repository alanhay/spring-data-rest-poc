package com.qikserve.hackdazespringdata.repository.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.qikserve.hackdazespringdata.domain.Order;
import com.qikserve.hackdazespringdata.domain.OrderLine;

@RepositoryEventHandler(Order.class)
public class OrderEventHandler {
	private static final Logger logger = LoggerFactory.getLogger(OrderEventHandler.class);

	@HandleBeforeCreate
	public void handleNewOrder(Order order) {
		logger.info("Order Event Listener triggered");

		for (OrderLine line : order.getOrderLines()) {
			line.getProduct().setStockCount(line.getProduct().getStockCount() - line.getQuantity());
		}
	}
}

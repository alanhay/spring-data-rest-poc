package uk.co.certait.poc.repository.listener;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import uk.co.certait.poc.domain.Order;
import uk.co.certait.poc.domain.OrderLine;

@RepositoryEventHandler(Order.class)
public class OrderEventHandler {
	private static final Logger logger = LoggerFactory.getLogger(OrderEventHandler.class);

	@HandleBeforeCreate
	public void handleNewOrder(Order order) {
		logger.info("Order Event Listener triggered");

		for (OrderLine line : order.getOrderLines()) {
			line.getProduct().setStockCount(line.getProduct().getStockCount() - line.getQuantity());
			line.setProductPrice(line.getProduct().getPrice());
		}
		
		order.setDateReceived(LocalDateTime.now());
	}
}

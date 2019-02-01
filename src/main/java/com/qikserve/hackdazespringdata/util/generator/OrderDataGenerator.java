package com.qikserve.hackdazespringdata.util.generator;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qikserve.hackdazespringdata.domain.Customer;
import com.qikserve.hackdazespringdata.domain.Order;
import com.qikserve.hackdazespringdata.domain.OrderLine;
import com.qikserve.hackdazespringdata.domain.Product;
import com.qikserve.hackdazespringdata.domain.repository.CustomerRepository;
import com.qikserve.hackdazespringdata.domain.repository.OrderRepository;
import com.qikserve.hackdazespringdata.domain.repository.ProductRepository;

@Component
public class OrderDataGenerator {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	public void generateData() {

		List<Product> products = productRepository.findAll();

		for (Customer customer : customerRepository.findAll()) {
			Order order = new Order();
			order.setCustomer(customer);
			order.setDateReceived(LocalDateTime.now().minusDays(RandomUtils.nextInt(10, 700)));
			order.setDateDispatched(order.getDateReceived().plusDays(RandomUtils.nextInt(1, 10)));

			for (int i = 0; i <= RandomUtils.nextInt(1, 5); ++i) {
				OrderLine line = new OrderLine();
				Product product = products.get(RandomUtils.nextInt(0, products.size()));
				line.setProduct(product);
				line.setProductPrice(product.getPrice());
				line.setQuantity(RandomUtils.nextInt(1, 10));
				order.addOrderLine(line);
			}

			orderRepository.save(order);
		}
	}
}

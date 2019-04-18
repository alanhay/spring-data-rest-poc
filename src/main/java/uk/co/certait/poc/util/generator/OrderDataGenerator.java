package uk.co.certait.poc.util.generator;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.certait.poc.domain.Customer;
import uk.co.certait.poc.domain.Order;
import uk.co.certait.poc.domain.OrderLine;
import uk.co.certait.poc.domain.Product;
import uk.co.certait.poc.repository.CustomerRepository;
import uk.co.certait.poc.repository.OrderRepository;
import uk.co.certait.poc.repository.ProductRepository;

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

package uk.co.certait.poc.domain.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import uk.co.certait.poc.domain.Address;
import uk.co.certait.poc.domain.Customer;
import uk.co.certait.poc.domain.Order;

@JsonPropertyOrder({ "forename", "surname", "dateOfBirth", "emailAddress", "address", "orderCount", "orderTotal",
		"orders" })
@Projection(name = "detail", types = Customer.class)
public interface CustomerDetailProjection extends CustomerSummaryProjection {

	LocalDate getDateOfBirth();

	String getEmailAddress();

	Integer getOrderCount();

	BigDecimal getOrderTotal();

	Set<Order> getOrders();

	Address getAddress();
}

package com.qikserve.hackdazespringdata.domain.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.qikserve.hackdazespringdata.domain.Address;
import com.qikserve.hackdazespringdata.domain.Customer;
import com.qikserve.hackdazespringdata.domain.Order;

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

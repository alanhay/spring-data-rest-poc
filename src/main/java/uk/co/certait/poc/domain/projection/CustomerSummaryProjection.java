package uk.co.certait.poc.domain.projection;

import org.springframework.data.rest.core.config.Projection;

import uk.co.certait.poc.domain.Customer;

@Projection(name = "summary", types = Customer.class)
public interface CustomerSummaryProjection {

	String getForename();

	String getSurname();

	// concatenate using basic SPEL - we can also invoke other service beans to add data
	// @Value(value = "#{target.address.houseNumber}, {#target.address.lineOne},
	// {#target.address.town}, {#target.address.postCode}")
	// String getAddress();
}

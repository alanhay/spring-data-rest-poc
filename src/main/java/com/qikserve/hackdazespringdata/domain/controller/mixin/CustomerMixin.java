package com.qikserve.hackdazespringdata.domain.controller.mixin;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.qikserve.hackdazespringdata.domain.Order;
import com.qikserve.hackdazespringdata.domain.controller.json.CustomerViews;

public class CustomerMixin extends BaseMixin {

	@JsonView(value = CustomerViews.Summary.class)
	Long id;

	@JsonView(value = CustomerViews.Summary.class)
	String forename;

	@JsonView(value = CustomerViews.Summary.class)
	String surname;

	@JsonView(value = CustomerViews.Summary.class)
	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dateOfBirth;

	@JsonView(value = CustomerViews.Detailed.class)
	public Set<Order> orders;

}

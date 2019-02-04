package com.qikserve.hackdazespringdata.domain.controller.mixin;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qikserve.hackdazespringdata.domain.Order;

public class CustomerMixin extends BaseMixin {

	Long id;

	String forename;

	String surname;

	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dateOfBirth;

	public Set<Order> orders;

}

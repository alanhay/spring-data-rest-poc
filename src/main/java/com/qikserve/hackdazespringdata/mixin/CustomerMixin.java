package com.qikserve.hackdazespringdata.mixin;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class CustomerMixin {

	// date formatting can probably be set globally

	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dateOfBirth;

	@JsonProperty(access = Access.WRITE_ONLY)
	String password;

	@JsonProperty(access = Access.READ_ONLY)
	int orderCount;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonProperty(access = Access.READ_ONLY)
	LocalDate lastOrderDate;

	@JsonProperty(access = Access.READ_ONLY)
	BigDecimal orderTotal;
}

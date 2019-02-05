package com.qikserve.hackdazespringdata.mixin;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderMixin {

	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dateReceived;

	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dateDispatched;
}

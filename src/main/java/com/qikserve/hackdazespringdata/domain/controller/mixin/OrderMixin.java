package com.qikserve.hackdazespringdata.domain.controller.mixin;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderMixin extends BaseMixin{

	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dateReceived;

	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate dateDispatched;
}

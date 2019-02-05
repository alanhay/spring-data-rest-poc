package com.qikserve.hackdazespringdata.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

	@NotNull
	private String houseNumber;

	@NotNull
	private String line1;

	private String line2;

	@NotNull
	private String town;

	@NotNull
	private String postCode;
}

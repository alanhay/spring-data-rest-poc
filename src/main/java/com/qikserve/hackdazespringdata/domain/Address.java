package com.qikserve.hackdazespringdata.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

	private String houseNumber;

	private String line1;

	private String line2;

	private String town;

	private String postCode;
}

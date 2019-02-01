package com.qikserve.hackdazespringdata.domain;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer extends BaseEntity {

	private String forename;

	private String surname;

	private LocalDate dateOfBirth;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "customer")
	private Set<Order> orders;
}

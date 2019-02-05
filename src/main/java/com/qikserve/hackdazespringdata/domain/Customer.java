package com.qikserve.hackdazespringdata.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@SecondaryTable(name = "customer_summary_data_vw", pkJoinColumns = @PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "id"))
@Getter
@Setter
public class Customer extends BaseEntity {

	@NotNull
	private String forename;

	@NotNull
	private String surname;

	@NotNull
	private LocalDate dateOfBirth;

	@NotNull
	private String emailAddress;

	@NotNull
	private String password;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@Column(table = "customer_summary_data_vw")
	private Integer orderCount;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@Column(table = "customer_summary_data_vw")
	private BigDecimal orderTotal;

	@NotNull
	@Valid
	@Embedded
	private Address address;

	@OneToMany(mappedBy = "customer")
	private Set<Order> orders;

	public Integer getOrderCount() {
		return orderCount == null ? 0 : orderCount;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal == null ? BigDecimal.ZERO : orderTotal;
	}
}

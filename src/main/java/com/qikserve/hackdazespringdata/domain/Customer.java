package com.qikserve.hackdazespringdata.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
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

import org.springframework.format.annotation.DateTimeFormat;

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

	// FIXME THIS IS FOR QUERYING BY DATE PARAMS - FIND A BETTER WAY THAT RERQUIRING
	// THIS SPRING DEPENDENCY
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Setter(AccessLevel.NONE)
	@Column(table = "customer_summary_data_vw")
	private LocalDate lastOrderDate;

	@NotNull
	@Valid
	@Embedded
	private Address address;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@OneToMany(mappedBy = "customer")
	private Set<Order> orders;

	public Integer getOrderCount() {
		return orderCount == null ? 0 : orderCount;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal == null ? BigDecimal.ZERO : orderTotal;
	}

	public Set<Order> getOrders() {
		return Collections.unmodifiableSet(orders);
	}

	public void addOrder(Order order) {
		orders.add(order);

		if (order.getCustomer() != this) {
			order.setCustomer(this);
		}
	}

	public void removeOrder(Order order) {
		orders.remove(order);
	}
}

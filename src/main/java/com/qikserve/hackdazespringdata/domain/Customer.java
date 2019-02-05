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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@SecondaryTable(name = "customer_summary_data_vw", pkJoinColumns = @PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "id"))
@Getter
@Setter
public class Customer extends BaseEntity {

	private String forename;

	private String surname;

	private LocalDate dateOfBirth;

	@Column(table = "customer_summary_data_vw")
	private Integer orderCount;

	@Column(table = "customer_summary_data_vw")
	private BigDecimal orderTotal;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "customer")
	private Set<Order> orders;
}

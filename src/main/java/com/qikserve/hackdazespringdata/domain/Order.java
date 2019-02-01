package com.qikserve.hackdazespringdata.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity {

	private LocalDateTime dateReceived;

	private LocalDateTime dateDispatched;

	@ManyToOne
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "orders_to_csutomers_fk"))
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "orders_to_order_lines_fk"))
	private Set<OrderLine> orderLines;

	public Order() {
		orderLines = new HashSet<>();
	}

	public void addOrderLine(OrderLine line) {
		orderLines.add(line);
	}
}

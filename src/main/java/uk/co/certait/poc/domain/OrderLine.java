package uk.co.certait.poc.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_lines")
@Getter
@Setter
public class OrderLine extends BaseEntity {

	private int quantity;

	private BigDecimal productPrice;

	@ManyToOne
	@JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "order_lines_to_to_products_fk"))
	private Product product;
}

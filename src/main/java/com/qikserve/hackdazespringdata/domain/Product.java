package com.qikserve.hackdazespringdata.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends BaseEntity {

	private String sku;

	private String name;

	private BigDecimal price;

	private int stockCount;
}

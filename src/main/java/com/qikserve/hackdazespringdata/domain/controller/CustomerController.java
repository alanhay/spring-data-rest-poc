package com.qikserve.hackdazespringdata.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.qikserve.hackdazespringdata.domain.Customer;
import com.qikserve.hackdazespringdata.domain.controller.json.CustomerViews;
import com.qikserve.hackdazespringdata.service.CustomerService;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// https://spring.io/blog/2014/12/02/latest-jackson-integration-improvements-in-spring
	@JsonView(value = CustomerViews.Summary.class)
	@GetMapping
	public List<Customer> findAllWithSummaryView() {
		return customerService.findAll();
	}

	@GetMapping(params = "view=detailed")
	public List<Customer> findAllWithDetailView() {
		return customerService.findAll();
	}

	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.web
	@GetMapping(value = "/", params = "page")
	public Page<Customer> findAll(Pageable pageable) {
		return customerService.findAll(pageable);
	}

	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.web.basic.domain-class-converter
	@GetMapping("/{id}")
	public Customer get(@PathVariable("id") Customer customer) {
		return customer;
	}

	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.web.type-safe
	@GetMapping("/search")
	public Page<Customer> search(@QuerydslPredicate(root = Customer.class) Predicate predicate, Pageable pageable) {
		return customerService.findAll(predicate, pageable);
	}
}

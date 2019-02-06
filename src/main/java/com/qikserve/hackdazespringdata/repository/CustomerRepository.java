package com.qikserve.hackdazespringdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import com.qikserve.hackdazespringdata.domain.Customer;
import com.qikserve.hackdazespringdata.domain.QCustomer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer>,
		QuerydslBinderCustomizer<QCustomer> {

	@Override
	default void customize(QuerydslBindings bindings, QCustomer customer) {
		bindings.bind(customer.surname).first((path, value) -> path.likeIgnoreCase(value + "%"));

		bindings.bind(customer.lastOrderDate).first((path, value) -> path.goe(value));
	}
}

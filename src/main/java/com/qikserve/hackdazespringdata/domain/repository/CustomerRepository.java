package com.qikserve.hackdazespringdata.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.qikserve.hackdazespringdata.domain.Customer;

@Repository
public interface CustomerRepository
    extends JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {

}
package uk.co.certait.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import uk.co.certait.poc.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {

}

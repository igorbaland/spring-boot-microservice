package com.bal.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bal.orderservice.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long>{

}

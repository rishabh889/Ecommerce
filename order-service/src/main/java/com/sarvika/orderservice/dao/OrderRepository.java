package com.sarvika.orderservice.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sarvika.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

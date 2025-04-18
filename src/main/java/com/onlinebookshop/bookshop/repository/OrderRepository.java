package com.onlinebookshop.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebookshop.bookshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

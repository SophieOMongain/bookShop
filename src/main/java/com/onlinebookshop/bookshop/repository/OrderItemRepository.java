package com.onlinebookshop.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebookshop.bookshop.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

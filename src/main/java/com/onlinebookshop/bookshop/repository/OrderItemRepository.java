package com.onlinebookshop.bookshop.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookshop.bookshop.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);
    List<OrderItem> findByBookId(Long bookId);
}

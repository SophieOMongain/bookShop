package com.onlinebookshop.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookshop.bookshop.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}

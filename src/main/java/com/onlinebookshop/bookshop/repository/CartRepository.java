package com.onlinebookshop.bookshop.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebookshop.bookshop.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
    Optional<Cart> findByUserId(Long userId);
}
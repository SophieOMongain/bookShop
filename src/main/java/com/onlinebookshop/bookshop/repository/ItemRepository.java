package com.onlinebookshop.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebookshop.bookshop.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
}

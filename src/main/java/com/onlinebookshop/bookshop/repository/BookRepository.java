package com.onlinebookshop.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebookshop.bookshop.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
}


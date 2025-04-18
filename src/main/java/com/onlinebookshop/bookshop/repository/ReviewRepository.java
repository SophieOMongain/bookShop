package com.onlinebookshop.bookshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebookshop.bookshop.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
}

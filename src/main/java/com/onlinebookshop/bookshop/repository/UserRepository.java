package com.onlinebookshop.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebookshop.bookshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}

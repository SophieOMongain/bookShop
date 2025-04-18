package com.onlinebookshop.bookshop.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookshop.bookshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}

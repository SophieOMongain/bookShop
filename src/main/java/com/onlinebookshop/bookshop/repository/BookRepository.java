package com.onlinebookshop.bookshop.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebookshop.bookshop.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

 
    List<Book> findByTitleContainingIgnoreCase(String title, Sort sort);

    List<Book> findByAuthorContainingIgnoreCase(String author, Sort sort);

    List<Book> findByPublisherContainingIgnoreCase(String publisher, Sort sort);

    List<Book> findByCategoryContainingIgnoreCase(String category, Sort sort);
}

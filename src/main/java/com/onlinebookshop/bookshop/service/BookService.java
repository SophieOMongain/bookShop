package com.onlinebookshop.bookshop.service;

import com.onlinebookshop.bookshop.entity.Book;
import com.onlinebookshop.bookshop.factory.BookFactory;
import com.onlinebookshop.bookshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book createBook(String title, String author, String publisher, String isbn, Double price, String category, Integer stock, String imageUrl) {
        Book book = BookFactory.createBook(
            title, author, publisher, isbn, price, category, stock, imageUrl
        );
        return bookRepository.save(book);
    }

    public Book createBook(Book book) {
        Book newBook = BookFactory.createBook(
            book.getTitle(),
            book.getAuthor(),
            book.getPublisher(),
            book.getIsbn(),
            book.getPrice(),
            book.getCategory(),
            book.getStock(),
            book.getImageUrl()
        );
        return bookRepository.save(newBook);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existing = bookRepository.findById(id);
        
        if (existing.isPresent()) {
            Book book = existing.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublisher(updatedBook.getPublisher());
            book.setIsbn(updatedBook.getIsbn());
            book.setPrice(updatedBook.getPrice());
            book.setCategory(updatedBook.getCategory());
            book.setStock(updatedBook.getStock());
            book.setImageUrl(updatedBook.getImageUrl());
            return bookRepository.save(book);
        }
        return null;
    }
    
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String title, String author, String publisher, String category, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
       
        if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title, sort);
        }
        if (author != null) {
            return bookRepository.findByAuthorContainingIgnoreCase(author, sort);
        }
        if (publisher != null) {
            return bookRepository.findByPublisherContainingIgnoreCase(publisher, sort);
        }
        if (category != null) {
            return bookRepository.findByCategoryContainingIgnoreCase(category, sort);
        }
        return bookRepository.findAll(sort);
    }
}


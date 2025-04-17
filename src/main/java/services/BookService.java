package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.Book;
import factory.BookFactory;
import repository.BookRepository;
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
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    public Book createBook(String title, String author, String publisher, 
                           String isbn, Double price, String category, 
                           Integer stock, String imageUrl) {
        Book book = BookFactory.createBook(title, author, publisher, isbn, price, category, stock, imageUrl);
        
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book book = bookData.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublisher(updatedBook.getPublisher());
            book.setIsbn(updatedBook.getIsbn());
            book.setPrice(updatedBook.getPrice());
            book.setCategory(updatedBook.getCategory());
            book.setStock(updatedBook.getStock());
            book.setImageUrl(updatedBook.getImageUrl());
            return bookRepository.save(book);
        } else {
            return null;
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

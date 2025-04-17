package factory;

import entity.Book;

public class BookFactory {
    
    public static Book createBook(String title, String author, String publisher, String isbn, Double price, String category, Integer stock, String imageUrl) {
        
    	Book book = new Book();
    	
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setCategory(category);
        book.setStock(stock);
        book.setImageUrl(imageUrl);
        
        return book;
    }
}

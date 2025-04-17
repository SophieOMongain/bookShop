package Strategy;

import org.springframework.stereotype.Component;
import entity.Book;

@Component
public abstract class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(Book book) {
        return book.getPrice(); 
    }
}

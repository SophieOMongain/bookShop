package Strategy;

import org.springframework.stereotype.Component;
import entity.Book;

@Component
public abstract class PercentDiscountStrategy implements DiscountStrategy {

    private final double discountPercentage = 10.0; 
    
    @Override
    public double applyDiscount(Book book) {
        double discount = book.getPrice() * (discountPercentage / 100);
        return book.getPrice() - discount;
    }
}

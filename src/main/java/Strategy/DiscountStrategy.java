package Strategy;

import entity.Book;

public interface DiscountStrategy {
    double applyDiscount(Book book);
}

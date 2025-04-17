package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Strategy.DiscountStrategy;
import entity.Book;

@Service
public class DiscountService {

    private final DiscountStrategy discountStrategy;

    @Autowired
    public DiscountService(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculateDiscountedPrice(Book book) {
        return discountStrategy.applyDiscount(book);
    }
}


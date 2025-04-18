package com.onlinebookshop.bookshop.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.onlinebookshop.bookshop.entity.Book;

@Component
public abstract class PercentDiscountStrategy implements DiscountStrategy {

    private final double discountPercentage;
    public PercentDiscountStrategy(
        @Value("${bookshop.discount.percentage:10}") double discountPercentage
    ) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double applyDiscount(Book book) {
        double discount = book.getPrice() * (discountPercentage / 100.0);
        return book.getPrice() - discount;
    }
}

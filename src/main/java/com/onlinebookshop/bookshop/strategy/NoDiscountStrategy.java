package com.onlinebookshop.bookshop.strategy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.onlinebookshop.bookshop.entity.Book;

@Component
@Primary
public abstract class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(Book book) {
        return book.getPrice(); 
    }
}

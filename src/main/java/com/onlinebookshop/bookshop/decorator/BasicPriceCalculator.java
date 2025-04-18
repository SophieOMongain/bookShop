package com.onlinebookshop.bookshop.decorator;


import org.springframework.stereotype.Component;

import com.onlinebookshop.bookshop.entity.Book;

@Component
public class BasicPriceCalculator implements PriceCalculator {
    @Override
    public double calculatePrice(Book book) {
        return book.getPrice();
    }
}

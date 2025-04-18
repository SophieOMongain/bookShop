package com.onlinebookshop.bookshop.decorator;


import org.springframework.stereotype.Component;

import com.onlinebookshop.bookshop.entity.Book;

@Component
public class DiscountDecorator extends PriceCalculatorDecorator {
    private final double discountPct = 10.0; 

    public DiscountDecorator(PriceCalculator wrapped) {
        super(wrapped);
    }

    @Override
    public double calculatePrice(Book book) {
        double base = super.calculatePrice(book);
        return base * (1 - discountPct / 100.0);
    }
}

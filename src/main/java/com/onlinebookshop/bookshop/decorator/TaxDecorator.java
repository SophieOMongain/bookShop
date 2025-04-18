package com.onlinebookshop.bookshop.decorator;


import org.springframework.stereotype.Component;

import com.onlinebookshop.bookshop.entity.Book;

@Component
public class TaxDecorator extends PriceCalculatorDecorator {
    private final double taxRate = 5.0;

    public TaxDecorator(PriceCalculator wrapped) {
        super(wrapped);
    }

    @Override
    public double calculatePrice(Book book) {
        double discounted = super.calculatePrice(book);
        return discounted + (discounted * taxRate / 100.0);
    }
}

package com.onlinebookshop.bookshop.decorator;

import com.onlinebookshop.bookshop.entity.Book;

public abstract class PriceCalculatorDecorator implements PriceCalculator {
    protected final PriceCalculator wrapped;
    protected PriceCalculatorDecorator(PriceCalculator wrapped) {
        this.wrapped = wrapped;
    }
    @Override
    public double calculatePrice(Book book) {
        return wrapped.calculatePrice(book);
    }
}

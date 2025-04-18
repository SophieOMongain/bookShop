package com.onlinebookshop.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebookshop.bookshop.decorator.BasicPriceCalculator;
import com.onlinebookshop.bookshop.decorator.DiscountDecorator;
import com.onlinebookshop.bookshop.decorator.PriceCalculator;
import com.onlinebookshop.bookshop.decorator.TaxDecorator;
import com.onlinebookshop.bookshop.entity.Book;

@Service
public class PriceService {

    private final BasicPriceCalculator base;
    private final DiscountDecorator discount;
    private final TaxDecorator tax;

    @Autowired
    public PriceService(BasicPriceCalculator base, DiscountDecorator discount, TaxDecorator tax) {
        this.base = base;
        this.discount = discount;
        this.tax = tax;
    }

    public double calculateFinalPrice(Book book) {
        PriceCalculator withDiscount = new DiscountDecorator(base);
        PriceCalculator withTax = new TaxDecorator(withDiscount);
        return withTax.calculatePrice(book);
    }
}

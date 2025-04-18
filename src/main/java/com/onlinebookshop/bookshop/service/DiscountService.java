package com.onlinebookshop.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebookshop.bookshop.entity.Book;
import com.onlinebookshop.bookshop.strategy.DiscountStrategy;

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


package com.onlinebookshop.bookshop.strategy;

import com.onlinebookshop.bookshop.entity.Book;

public interface DiscountStrategy {
    double applyDiscount(Book book);
}

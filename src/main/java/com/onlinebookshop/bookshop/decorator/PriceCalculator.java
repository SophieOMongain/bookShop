package com.onlinebookshop.bookshop.decorator;

import com.onlinebookshop.bookshop.entity.Book;

public interface PriceCalculator {
   
    double calculatePrice(Book book);
}

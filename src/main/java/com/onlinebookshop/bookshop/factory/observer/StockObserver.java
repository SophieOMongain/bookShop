package com.onlinebookshop.bookshop.factory.observer;

import com.onlinebookshop.bookshop.entity.Book;

public interface StockObserver {
   
    void onStockChange(Book book, int oldStock, int newStock);
}

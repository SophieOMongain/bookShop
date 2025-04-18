package com.onlinebookshop.bookshop.factory.observer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.onlinebookshop.bookshop.entity.Book;

@Component
public class AdminNotificationObserver implements StockObserver {
    private static final Logger logger = LoggerFactory.getLogger(AdminNotificationObserver.class);

    @Override
    public void onStockChange(Book book, int oldStock, int newStock) {
        logger.info("Stock for '{}' changed: {} → {}", book.getTitle(), oldStock, newStock);
    }
}

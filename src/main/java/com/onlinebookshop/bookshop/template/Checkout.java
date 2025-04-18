package com.onlinebookshop.bookshop.template;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlinebookshop.bookshop.entity.Book;
import com.onlinebookshop.bookshop.entity.Cart;
import com.onlinebookshop.bookshop.entity.Item;
import com.onlinebookshop.bookshop.entity.Order;
import com.onlinebookshop.bookshop.entity.OrderItem;
import com.onlinebookshop.bookshop.factory.observer.StockObserver;

import java.util.ArrayList;
import java.util.List;

@Component
public class Checkout extends CheckoutTemplate {

    private final List<StockObserver> observers;

    @Autowired
    public Checkout(List<StockObserver> observers) {
        this.observers = observers;
    }

    @Override
    protected void validateCart(Cart cart) {
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
    }

    @Override
    protected void applyDiscounts(Cart cart) {
    }

    @Override
    protected void calculateTotal(Cart cart) {
        double total = 0.0;
        for (Item item : cart.getItems()) {
            total += item.getBook().getPrice() * item.getQuantity();
        }
        cart.setTotal(total);
    }

    @Override
    protected Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setTotal(cart.getTotal());

        List<OrderItem> orderItems = new ArrayList<>();
        for (Item item : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setBook(item.getBook());
            oi.setQuantity(item.getQuantity());
            oi.setPrice(item.getBook().getPrice());
            orderItems.add(oi);
        }
        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    protected void updateStock(Cart cart) {
        for (Item item : cart.getItems()) {
            Book book = item.getBook();
            int oldStock = book.getStock();
            int newStock = oldStock - item.getQuantity();
            book.setStock(newStock);
            notifyObservers(book, oldStock, newStock);
        }
    }

    private void notifyObservers(Book book, int oldStock, int newStock) {
        for (StockObserver observer : observers) {
            observer.onStockChange(book, oldStock, newStock);
        }
    }
}

package com.onlinebookshop.bookshop.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlinebookshop.bookshop.entity.Book;
import com.onlinebookshop.bookshop.entity.Cart;
import com.onlinebookshop.bookshop.entity.Item;
import com.onlinebookshop.bookshop.entity.User;
import com.onlinebookshop.bookshop.repository.BookRepository;
import com.onlinebookshop.bookshop.repository.CartRepository;
import com.onlinebookshop.bookshop.repository.ItemRepository;
import com.onlinebookshop.bookshop.repository.UserRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ItemRepository itemRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Cart getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        return (cart != null) ? cart : createCartForUser(userId);
    }

    public Cart createCartForUser(Long userId) {
        return userRepository.findById(userId)
            .map(user -> {
                Cart cart = new Cart();
                cart.setUser(user);
                cart.setItems(new ArrayList<>());
                return cartRepository.save(cart);
            })
            .orElse(null);
    }

    public Cart addItemToCart(Long userId, Long bookId, Integer quantity) {
        Cart cart = getCartByUserId(userId);
        
        if (cart == null) return null;
        return bookRepository.findById(bookId).map(book -> {
            List<Item> items = cart.getItems();
            for (Item item : items) {
                if (item.getBook().getId().equals(bookId)) {
                    item.setQuantity(item.getQuantity() + quantity);
                    itemRepository.save(item);
                    return cartRepository.save(cart);
                }
            }
            Item newItem = new Item();
            newItem.setCart(cart);
            newItem.setBook(book);
            newItem.setQuantity(quantity);
            itemRepository.save(newItem);
            items.add(newItem);
            cart.setItems(items);
            return cartRepository.save(cart);
        }).orElse(cart);
    }

    public Cart removeItemFromCart(Long userId, Long itemId) {
        Cart cart = getCartByUserId(userId);
        
        if (cart == null) return null;
        return itemRepository.findById(itemId).map(item -> {
            if (item.getCart().getId().equals(cart.getId())) {
                cart.getItems().remove(item);
                itemRepository.delete(item);
                return cartRepository.save(cart);
            }
            return cart;
        }).orElse(cart);
    }

    public Cart updateItemQuantity(Long userId, Long itemId, Integer newQuantity) {
        Cart cart = getCartByUserId(userId);
        
        if (cart == null) return null;
        return itemRepository.findById(itemId).map(item -> {
            if (item.getCart().getId().equals(cart.getId())) {
                item.setQuantity(newQuantity);
                itemRepository.save(item);
                return cartRepository.save(cart);
            }
            return cart;
        }).orElse(cart);
    }

    public Cart clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        
        if (cart == null) return null;
        List<Item> items = new ArrayList<>(cart.getItems());
        for (Item item : items) {
            itemRepository.delete(item);
        }
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}

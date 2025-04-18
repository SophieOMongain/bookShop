package com.onlinebookshop.bookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.onlinebookshop.bookshop.entity.Cart;
import com.onlinebookshop.bookshop.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return (cart != null)
            ? ResponseEntity.ok(cart)
            : ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<Cart> addItem(
        @PathVariable Long userId,
        @RequestParam Long bookId,
        @RequestParam Integer quantity
    ) {
        Cart cart = cartService.addItemToCart(userId, bookId, quantity);
        return (cart != null)
            ? ResponseEntity.ok(cart)
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<Cart> removeItem( @PathVariable Long userId, @PathVariable Long itemId) {
        Cart cart = cartService.removeItemFromCart(userId, itemId);
        return (cart != null)
            ? ResponseEntity.ok(cart)
            : ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}/items/{itemId}")
    public ResponseEntity<Cart> updateItemQuantity( @PathVariable Long userId, @PathVariable Long itemId, @RequestParam Integer quantity ) {
        Cart cart = cartService.updateItemQuantity(userId, itemId, quantity);
        return (cart != null)
            ? ResponseEntity.ok(cart)
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        Cart cart = cartService.clearCart(userId);
        return (cart != null)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}

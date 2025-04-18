package com.onlinebookshop.bookshop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.onlinebookshop.bookshop.entity.OrderItem;
import com.onlinebookshop.bookshop.service.OrderItemService;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getByOrderId(@PathVariable Long orderId) {
        List<OrderItem> items = orderItemService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable Long id) {
        OrderItem item = orderItemService.getOrderItemById(id);
        return (item != null)
            ? ResponseEntity.ok(item)
            : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem created = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(
        @PathVariable Long id,
        @RequestBody OrderItem updatedOrderItem
    ) {
        OrderItem item = orderItemService.updateOrderItem(id, updatedOrderItem);
        return (item != null)
            ? ResponseEntity.ok(item)
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        boolean deleted = orderItemService.deleteOrderItem(id);
        return deleted
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}


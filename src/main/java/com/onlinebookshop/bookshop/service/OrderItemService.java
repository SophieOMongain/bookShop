package com.onlinebookshop.bookshop.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlinebookshop.bookshop.entity.OrderItem;
import com.onlinebookshop.bookshop.repository.OrderItemRepository;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
    
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
    
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }
    
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
    
    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        return orderItemRepository.findById(id)
            .map(item -> {
                item.setQuantity(updatedOrderItem.getQuantity());
                item.setPrice(updatedOrderItem.getPrice());
                return orderItemRepository.save(item);
            })
            .orElse(null);
    }

    public boolean deleteOrderItem(Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


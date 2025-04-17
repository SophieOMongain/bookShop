package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.OrderItem;
import repository.OrderItemRepository;
import java.util.List;
import java.util.Optional;

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

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        return orderItemRepository.findById(id).map(item -> {
            item.setQuantity(updatedOrderItem.getQuantity());
            item.setPrice(updatedOrderItem.getPrice());
            return orderItemRepository.save(item);
        }).orElse(null);
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}


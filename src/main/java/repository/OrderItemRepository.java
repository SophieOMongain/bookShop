package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

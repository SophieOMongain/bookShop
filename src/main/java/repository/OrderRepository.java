package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

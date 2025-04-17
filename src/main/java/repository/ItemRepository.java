package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
}

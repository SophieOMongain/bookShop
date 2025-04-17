package repository;


import org.springframework.data.jpa.repository.JpaRepository;
import entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
}

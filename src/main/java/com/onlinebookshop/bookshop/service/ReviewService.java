package com.onlinebookshop.bookshop.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlinebookshop.bookshop.entity.Review;
import com.onlinebookshop.bookshop.repository.ReviewRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }
    
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
    public Review updateReview(Long id, Review updatedReview) {
        return reviewRepository.findById(id)
            .map(review -> {
                review.setRating(updatedReview.getRating());
                review.setComment(updatedReview.getComment());
                return reviewRepository.save(review);
            })
            .orElse(null);
    }

    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
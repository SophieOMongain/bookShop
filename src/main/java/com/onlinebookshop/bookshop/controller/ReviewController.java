package com.onlinebookshop.bookshop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.onlinebookshop.bookshop.entity.Review;
import com.onlinebookshop.bookshop.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getByBookId(@PathVariable Long bookId) {
        List<Review> list = reviewService.getReviewsByBookId(bookId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getByUserId(@PathVariable Long userId) {
        List<Review> list = reviewService.getReviewsByUserId(userId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        return (review != null)
            ? ResponseEntity.ok(review)
            : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review created = reviewService.createReview(review);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
        @PathVariable Long id,
        @RequestBody Review updatedReview
    ){
        Review review = reviewService.updateReview(id, updatedReview);
        return (review != null)
            ? ResponseEntity.ok(review)
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        boolean deleted = reviewService.deleteReview(id);
        return deleted
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}

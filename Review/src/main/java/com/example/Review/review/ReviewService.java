package com.example.Review.review;

import java.util.List;

public interface ReviewService {

       public  List<Review>  getAllReview(String companyId);
       public  Review addReview(String companyId, Review review);

       public Review updateReview(Long reviewId,Review updatedReview);

       public  List<Review> getReviewByid(String companyId);

       public  boolean removeReview(Long reviewId);
}

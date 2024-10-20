package com.example.Review.review.impl;



import com.example.Review.review.Review;
import com.example.Review.review.ReviewRepository;
import com.example.Review.review.ReviewService;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {

  ReviewRepository reviewRepository;


  public ReviewServiceImpl(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }




  @Override
  public List<Review> getAllReview(String companyId) {
             List<Review> reviews  =  reviewRepository.findByCompanyId(companyId);
    System.out.println(reviews);
             if(reviews!=null){
                  return  reviews;
             }
             else
                return  null;

  }

  @Override
  public Review addReview(String companyId,Review review) {
      if(companyId!=null&& review!=null){
        System.out.println("review is ...." + review);
            review.setCompanyId(companyId);
            return  reviewRepository.save(review);
      }
    return null;
  }

  @Override
  public Review updateReview(Long reviewId, Review updatedReview) {
         Review review =   reviewRepository.findById(reviewId).orElse(null);
         if(review!=null){
               review.setTitle(updatedReview.getTitle());
               review.setDescription(updatedReview.getDescription());
               review.setRating(updatedReview.getRating());
               review.setCompanyId(updatedReview.getCompanyId());
             return reviewRepository.save(review);
         }
         return  null;

  }

  @Override
  public List<Review> getReviewByid(String companyId) {
            List<Review> reviews = reviewRepository.findByCompanyId(companyId);
            if(reviews!=null){
                   return  reviews;
            }
            else
              return  null;
  }

  @Override
  public boolean removeReview(Long reviewId) {
         if(this.reviewRepository.existsById(reviewId)){
                this.reviewRepository.deleteById(reviewId);
                return true;
         }
    return false;
  }
}

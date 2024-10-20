package com.example.Review.review;



import com.example.Review.message.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/company")
public class ReviewController<companyId> {

  ReviewService reviewService;

  ReviewMessageProducer reviewMessageProducer;


  public ReviewController(ReviewService reviewService,ReviewMessageProducer reviewMessageProducer) {
    this.reviewService = reviewService;
    this.reviewMessageProducer = reviewMessageProducer;
  }



@GetMapping("/getAllReviews")
  public ResponseEntity<List<Review>>  getAllReviews(@RequestParam String  companyId){
  System.out.println(companyId + " company id is ");
        List<Review> reviews = this.reviewService.getAllReview(companyId);
        if(reviews!=null){
             return  new ResponseEntity<>(reviews, HttpStatus.OK);
        }else {
           return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
  }


    @PostMapping("/addReview")
    public ResponseEntity<String> addReview(@RequestParam String  companyId,@RequestBody  Review review){
      System.out.println("companyId is " + companyId);
         Review review1 = this.reviewService.addReview(companyId,review);
      System.out.println(review1);
         if(review1!=null){
           reviewMessageProducer.sendMessage(review1);
             return  new ResponseEntity<>("one review added",HttpStatus.OK);
         }
         else
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/updateReview/")
    public  ResponseEntity<String> updateReview(@RequestParam Long reviewId,@RequestBody Review review){
       Review review1 =  this.reviewService.updateReview(reviewId,review);
       if(review1!=null){
          return  new ResponseEntity<>("review updated ", HttpStatus.OK);
       }
        return  null;
    }

    @GetMapping("/getById")
    public  ResponseEntity<List<Review>> getReviewId(@RequestParam  String companyId){
      System.out.println("company id is " + companyId);
         List<Review> reviews =  this.reviewService.getReviewByid(companyId);
         if(reviews!=null)
            return  new ResponseEntity<>(reviews,HttpStatus.OK);
         else
           return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/removeReview/")
    public ResponseEntity<String> removeReview(@RequestParam Long reviewId){
        boolean result =  this.reviewService.removeReview(reviewId);
         if(result==true)
             return  new ResponseEntity<>("review deleted successfully",HttpStatus.OK);
         else
            return  new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/getAverageRating")
    public  Double  getAverageReview(@RequestParam String companyId){
       List<Review> reviewList = this.reviewService.getAllReview(companyId);
       return  reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}

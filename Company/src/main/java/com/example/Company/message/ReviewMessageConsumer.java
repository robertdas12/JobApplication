package com.example.Company.message;

import com.example.Company.Dto.ReviewMessage;
import com.example.Company.company.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class ReviewMessageConsumer {

    private  final CompanyService companyService;

  public ReviewMessageConsumer(CompanyService companyService) {
    this.companyService = companyService;
  }

  @RabbitListener(queues = "companyRatingQueue")
  public   void consumeMessage(ReviewMessage reviewMessage){
     this.companyService.updateCompanyRating(reviewMessage);
  }
}

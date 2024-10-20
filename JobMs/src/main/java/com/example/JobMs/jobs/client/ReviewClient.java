package com.example.JobMs.jobs.client;

import com.example.JobMs.jobs.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "REVIEW-MS")
public interface ReviewClient {

    @GetMapping("/company/getById")
     public List<Review> getReview(@RequestParam String companyId);
}

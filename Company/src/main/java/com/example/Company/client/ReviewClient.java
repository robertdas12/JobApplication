package com.example.Company.client;

import com.example.Company.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "REVIEW-MS")
public interface ReviewClient {

    @GetMapping("/company/getAverageRating")
     public Double getAverageRating(@RequestParam String companyId);
}

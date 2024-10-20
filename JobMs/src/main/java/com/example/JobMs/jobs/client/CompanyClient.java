package com.example.JobMs.jobs.client;


import com.example.JobMs.jobs.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-MS")
public interface CompanyClient {

         @GetMapping("/company/{companyId}")
        Company  getCompany(@PathVariable String companyId);
}

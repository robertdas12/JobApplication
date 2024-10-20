package com.example.Company.company.impl;


import com.example.Company.Dto.ReviewMessage;
import com.example.Company.client.ReviewClient;
import com.example.Company.company.Company;
import com.example.Company.company.CompanyRepository;
import com.example.Company.company.CompanyService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {

 private CompanyRepository companyRepository;
 private ReviewClient reviewClient;

  public CompanyServiceImpl(CompanyRepository companyRepository,ReviewClient reviewClient) {
    this.companyRepository = companyRepository;
    this.reviewClient = reviewClient;
  }



  @Override
  public Company addCompany(Company company) {
      Company company1 =    this.companyRepository.save(company);
      return  company1;
  }

  @Override
  public List<Company> getAllCompany() {
    List<Company> companies =   this.companyRepository.findAll();
    if(companies!=null){
      return companies;
    }
    return null;
  }

  @Override
  public Company getById(String id) {
    Optional<Company> company =  this.companyRepository.findById(id);
     if(company.isPresent()){
          return  company.get();
     }
     else {
        return  null;
     }
  }

  @Override
  public Company updateCompany(String id, Company company) {
      Optional<Company>  company1 = this.companyRepository.findById(id);
       if(company1.isPresent()){
            Company newcompany =  company1.get();
            newcompany.setDescription(company.getDescription());
            newcompany.setName(company.getName());
            return  newcompany;
       }
    return null;
  }

  @Override
  public boolean deleteCompanyId(String id) {
        if(companyRepository.existsById(id)){
             companyRepository.deleteById(id);
             return  true;
        }
        else {
           return  false;
        }
  }

  @Override
  public void updateCompanyRating(ReviewMessage reviewMessage) {
    System.out.println(reviewMessage.getDescription());
     Company company = this.companyRepository.findById(reviewMessage.getCompanyId())
       .orElseThrow(()->new NotFoundException("company not found" + reviewMessage.getCompanyId()));

     Double averageRating = this.reviewClient.getAverageRating(reviewMessage.getCompanyId());

     company.setRating(averageRating);
     this.companyRepository.save(company);


  }
}

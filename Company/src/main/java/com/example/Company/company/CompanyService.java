package com.example.Company.company;

import com.example.Company.Dto.ReviewMessage;

import java.util.List;

public interface CompanyService {

    public Company addCompany(Company company);
    public List<Company> getAllCompany();

    public  Company getById(String id);

    public   Company updateCompany(String id,Company company);

    public  boolean deleteCompanyId(String id);

    public  void updateCompanyRating(ReviewMessage reviewMessage);
}

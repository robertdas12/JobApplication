package com.example.Company.company;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository  extends MongoRepository<Company,String> {
}

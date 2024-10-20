package com.example.Company.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMessage {
  private  Long id;
  private String title;
  private String description;
  private  double rating;
  private String companyId;

}

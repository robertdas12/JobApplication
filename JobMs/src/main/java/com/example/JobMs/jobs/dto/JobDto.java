package com.example.JobMs.jobs.dto;

import com.example.JobMs.jobs.external.Company;
import com.example.JobMs.jobs.external.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

  private Long id;
  private String title;
  private String description;
  private String minSalary;
  private String maxSalary;
  private String location;
  private Company company;
  private List<Review> reviews;
}

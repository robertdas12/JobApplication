package com.example.Review.review;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="review123")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
     private  String title;
     private String description;
     private  double rating;
     private   String companyId;

}

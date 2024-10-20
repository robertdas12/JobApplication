package com.example.Review.review;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

             public List<Review> findByCompanyId(String companyId);
}

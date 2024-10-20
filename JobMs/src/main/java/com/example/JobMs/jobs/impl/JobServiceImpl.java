package com.example.JobMs.jobs.impl;


import com.example.JobMs.jobs.Job;
import com.example.JobMs.jobs.JobRepository;
import com.example.JobMs.jobs.JobService;
import com.example.JobMs.jobs.client.CompanyClient;
import com.example.JobMs.jobs.client.ReviewClient;
import com.example.JobMs.jobs.dto.JobDto;
import com.example.JobMs.jobs.external.Company;
import com.example.JobMs.jobs.external.Review;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

private    JobRepository jobRepository;

 private  ModelMapper mapper;

 private CompanyClient companyClient;

 private ReviewClient reviewClient;

 int amount = 0;

  public JobServiceImpl(JobRepository jobRepository,ModelMapper mapper,CompanyClient companyClient,ReviewClient reviewClient) {
    this.jobRepository = jobRepository;
    this.mapper = mapper;
    this.companyClient = companyClient;
    this.reviewClient = reviewClient;
  }


  @Override
  public Job createJob(Job job) {
    return    this.jobRepository.save(job);
  }

  @Override
//  @CircuitBreaker(name = "jobCompanyReviewCB", fallbackMethod = "fallback")
  @Retry(name = "jobCompanyReviewCB", fallbackMethod = "fallback")
  public  List<JobDto> getAllJob() {
    System.out.println("No of tried " + ++amount);
      List<Job>  jobs = jobRepository.findAll();
      List<JobDto> jobDtos = new ArrayList<>();
     return  jobs.stream().map(this::getJobWithCompanyDto).collect(Collectors.toList());

  }

   public JobDto getJobWithCompanyDto(Job job){
     JobDto jobDto =   mapper.map(job, JobDto.class);

           Company  company =  this.companyClient.getCompany(job.getCompanyId());
             List<Review> reviews =  this.reviewClient.getReview(job.getCompanyId());

          jobDto.setCompany(company);
          jobDto.setReviews(reviews);

             return jobDto;

/*     System.out.println("company id is " + job.getCompanyId());
     RestTemplate restTemplate = new RestTemplate();
     Company company = restTemplate.getForObject("http://localhost:9393/company/"+job.getCompanyId(), Company.class);
     ResponseEntity<List<Review>> responseEntity =
      restTemplate.exchange("http://localhost:9595/company/getById?companyId=" + company.getId(),
      HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {});*/


   }

  public List<JobDto> fallback(Throwable throwable) {
    List<JobDto> fallbackList = new ArrayList<>();
    // Optionally, you can log the exception or add a dummy JobDto object to the list
    return fallbackList;
  }


  @Override
  public JobDto getJobById(Long id) {

       Job job =  jobRepository.findById(id).orElse(null);
       if(job!=null){
             return  getJobWithCompanyDto(job);
       }
       else
         return  null;
  }

  @Override
  public Job updateJob(Long id, Job job) {
       Optional<Job> job1 =  this.jobRepository.findById(id);
       if(job1.isPresent()){
               job1.get().setTitle(job.getTitle());
               job1.get().setDescription(job.getDescription());
               job1.get().setMinSalary(job.getMinSalary());
               job1.get().setMaxSalary(job.getMaxSalary());
               job1.get().setLocation(job.getLocation());
               job1.get().setCompanyId(job.getCompanyId());
             return  this.jobRepository.save(job1.get());
       }
    return null;
  }



  @Override
  public boolean  deleteJob(Long id) {
       if(this.jobRepository.existsById(id)){
             jobRepository.deleteById(id);
             return  true;
       }
       else
         return  false;
  }


}

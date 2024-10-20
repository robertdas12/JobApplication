package com.example.JobMs.jobs;


import com.example.JobMs.jobs.dto.JobDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {


  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  JobService jobService;



    @PostMapping("/createJob")
    public ResponseEntity<Job> createJob(@RequestBody Job job){
          Job job1 =   this.jobService.createJob(job);
          if(job1!=null){
               return   new ResponseEntity<>(job1, HttpStatus.CREATED);
          }else{
              return  new ResponseEntity<>(null);
          }
    }


    @GetMapping("/getAllJob")
    public ResponseEntity<List<JobDto>>  getAllJobs(){
      List<JobDto> listJob =   this.jobService.getAllJob();
       if(listJob!=null){
           return new ResponseEntity<>(listJob,HttpStatus.OK);
       }
       else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }


    @GetMapping("/getJobById")
    public ResponseEntity<JobDto> getJobById(@RequestParam  Long id){
          JobDto job  =   this.jobService.getJobById(id);
          if(job!=null){
                return  new ResponseEntity<>(job,HttpStatus.OK);
          }else {
             return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
    }

    @PutMapping("/updatejob/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id,@RequestBody Job job){
           Job job1 = this.jobService.updateJob(id,job);
           if(job1!=null){
                   return  new ResponseEntity<>(job1,HttpStatus.OK);
           }
           else{
              return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
    }


    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteJob(@PathVariable Long id){
           boolean b =  this.jobService.deleteJob(id);
           if(b==true){
              return  new ResponseEntity<>("one job deleted",HttpStatus.OK);
           }else {
             return  new ResponseEntity<>("id not present ", HttpStatus.NOT_FOUND);
           }
    }


}

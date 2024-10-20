package com.example.JobMs.jobs;

import com.example.JobMs.jobs.dto.JobDto;

import java.util.List;

public interface JobService {

     public Job createJob(Job job);
     public  List<JobDto> getAllJob();

     public JobDto getJobById(Long id);

     public Job  updateJob(Long id , Job job);

     public boolean deleteJob(Long id);
}

package com.example.JobMs.jobs.configuration;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {


   @Bean
     public ModelMapper getmapper(){
        return  new ModelMapper();
     }

}

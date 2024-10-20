package com.example.Company.company;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {


  public CompanyController(CompanyService service) {
    this.service = service;
  }

  CompanyService service;




      @PostMapping("/addCompany")
       public ResponseEntity<String> addCompany(@RequestBody Company company){
        System.out.println("company message in controller " + company);
             Company company1 =   this.service.addCompany(company);
             if(company1!=null)
             return new ResponseEntity<>("company created sucessfully", HttpStatus.CREATED);
             else
               return  null;
       }



        @GetMapping("/getAll")
       public ResponseEntity<List<Company>> getAllCompany(){
        List<Company> companies = this.service.getAllCompany();
          System.out.println("all companies " + companies);
        if(companies!=null){
            return new ResponseEntity<>(companies,HttpStatus.OK);
        }
        else {
           return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       }


       @PutMapping("/updateCompany/{id}")
       public ResponseEntity<String> updateCompany(@PathVariable String id,@RequestBody Company company){
              Company  updateCompany =     this.service.updateCompany(id,company);
               if(updateCompany!=null)
            return new ResponseEntity<>("company updated successfully ",HttpStatus.OK);
               else
                 return  null;
       }


       @GetMapping("/{id}")
       public ResponseEntity<Company> getCompanyById(@PathVariable String id){
          Company company =     this.service.getById(id);
          if(company!=null){
              return  new ResponseEntity<>(company,HttpStatus.OK);
          }else {
              return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
       }


       @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteCompany(@PathVariable  String id){
      boolean b =   this.service.deleteCompanyId(id);
          if(b==true){
              return  new ResponseEntity<>("company deleted sucessfully ", HttpStatus.OK);
          }else {
             return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
  }

}

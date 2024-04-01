package org.example.jobposting.controller;

import org.example.jobposting.dto.JobPostingDTO;
import org.example.jobposting.entity.Enum.Contract;
import org.example.jobposting.entity.Enum.JobType;
import org.example.jobposting.service.serviceImpl.JobPostingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/jobPosting")
public class JobPostingController {
    @Autowired
    JobPostingServiceImpl jobPostingServiceImpl;

    @GetMapping("/all-available")
    public ResponseEntity<List<JobPostingDTO>> showAllAvailableJobPosting(){
        List<JobPostingDTO> jobPostingDTOS=jobPostingServiceImpl.showAllAvailableJobPosting();
        return new ResponseEntity<>(jobPostingDTOS, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<JobPostingDTO>> showAllJobPosting(){
        List<JobPostingDTO> jobPostingDTOS=jobPostingServiceImpl.showAllJobPosting();
        return new ResponseEntity<>(jobPostingDTOS, HttpStatus.OK);
    }
    @GetMapping("/get-by-contract/{contract}")
    public ResponseEntity<List<JobPostingDTO>> findJobPostingByContract(@PathVariable(value = "contract") Contract contract){
        List<JobPostingDTO> jobPostingDTOS=jobPostingServiceImpl.findJobPostingByContract(contract);
        return new ResponseEntity<>(jobPostingDTOS, HttpStatus.OK);
    }
    @GetMapping("/get-by-job-type/{jobType}")
    public ResponseEntity<List<JobPostingDTO>> findJobPostingByJobType(@PathVariable(value = "jobType") JobType jobType){
        List<JobPostingDTO> jobPostingDTOS=jobPostingServiceImpl.findJobPostingByJobType(jobType);
        return new ResponseEntity<>(jobPostingDTOS, HttpStatus.OK);
    }
    @GetMapping("/get-by-company-id/{companyId}")
    public ResponseEntity<List<JobPostingDTO>> findJobPostingByCompanyId(@PathVariable(value = "companyId") Long companyId){
        List<JobPostingDTO> jobPostingDTOS=jobPostingServiceImpl.showAllJobPostingByCompanyId(companyId);
        return new ResponseEntity<>(jobPostingDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobPostingDTO> getJobPostingById(@PathVariable(value = "id") Long id){
        JobPostingDTO jobPostingDTO = jobPostingServiceImpl.getJobPostingById(id);
        return new ResponseEntity<>(jobPostingDTO, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<JobPostingDTO> addJobPosting(@RequestBody JobPostingDTO jobPostingDTO){
        JobPostingDTO jobPosting=jobPostingServiceImpl.addJobPosting(jobPostingDTO);
        return new ResponseEntity<>(jobPosting, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<JobPostingDTO> updateJobPosting(@RequestBody JobPostingDTO jobPostingDTO, @PathVariable Long id){
        JobPostingDTO jobPosting=jobPostingServiceImpl.updateJobPosting(id, jobPostingDTO);
        return new ResponseEntity<>(jobPosting, HttpStatus.OK);
    }
    @PutMapping("close/{id}")
    public HashMap<String,Boolean> closeJobPosting(@PathVariable(value = "id") Long id){
        JobPostingDTO jobPostingDTO=jobPostingServiceImpl.getJobPostingById(id);
        HashMap<String,Boolean> response=new HashMap<>();
        if(jobPostingServiceImpl.closeJobPosting(jobPostingDTO)){
            response.put("delete",Boolean.TRUE);
        }
        return response;
    }
}
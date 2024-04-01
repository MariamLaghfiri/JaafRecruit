package org.example.jobseeker.controller;

import org.example.jobseeker.dto.JobSeekerDTO;
import org.example.jobseeker.entity.Enum.Availability;
import org.example.jobseeker.service.serviceImpl.JobSeekerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/jobSeeker")
public class JobSeekerController {
    @Autowired
    JobSeekerServiceImpl jobSeekerServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<List<JobSeekerDTO>> showAllJobSeeker(){
        List<JobSeekerDTO> jobSeekerDTOS=jobSeekerServiceImpl.showAllJobSeeker();
        return new ResponseEntity<>(jobSeekerDTOS, HttpStatus.OK);
    }
    @GetMapping("/get-by-availability/{availability}")
    public ResponseEntity<List<JobSeekerDTO>> findJobSeekerByCategory(@PathVariable(value = "availability") Availability availability){
        List<JobSeekerDTO> jobSeekerDTOS=jobSeekerServiceImpl.findJobSeekerByAvailability(availability);
        return new ResponseEntity<>(jobSeekerDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobSeekerDTO> getJobSeekerById(@PathVariable(value = "id") Long id){
        JobSeekerDTO jobSeekerDTO = jobSeekerServiceImpl.getJobSeekerById(id);
        return new ResponseEntity<>(jobSeekerDTO, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<JobSeekerDTO> addJobSeeker(@RequestBody JobSeekerDTO jobSeekerDTO, @RequestHeader Long userId){
        jobSeekerDTO.setUserId(userId);
        jobSeekerDTO.setDeleted(Boolean.FALSE);
        jobSeekerDTO.setJobAlert(Boolean.FALSE);
        JobSeekerDTO jobSeeker=jobSeekerServiceImpl.addJobSeeker(jobSeekerDTO);
        return new ResponseEntity<>(jobSeeker, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<JobSeekerDTO> updateJobSeeker(@RequestBody JobSeekerDTO jobSeekerDTO, @PathVariable Long id, @RequestHeader Long userId){
        jobSeekerDTO.setUserId(userId);
        jobSeekerDTO.setDeleted(Boolean.FALSE);
        jobSeekerDTO.setJobAlert(Boolean.FALSE);
        JobSeekerDTO jobSeeker=jobSeekerServiceImpl.updateJobSeeker(id, jobSeekerDTO);
        return new ResponseEntity<>(jobSeeker, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public HashMap<String,Boolean> deleteJobSeeker(@PathVariable(value = "id") Long id){
        JobSeekerDTO jobSeekerDTO=jobSeekerServiceImpl.getJobSeekerById(id);
        HashMap<String,Boolean> response=new HashMap<>();
        if(jobSeekerServiceImpl.deleteJobSeeker(jobSeekerDTO)){
            response.put("delete",Boolean.TRUE);
        }
        return response;
    }
}
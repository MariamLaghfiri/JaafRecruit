package org.example.application.controller;

import org.example.application.dto.ApplicationDTO;
import org.example.application.entity.Enum.AppStatus;
import org.example.application.service.serviceImpl.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/application")
public class ApplicationController {
    @Autowired
    ApplicationServiceImpl applicationServiceImpl;

    @GetMapping("/all-by-user-id")
    public ResponseEntity<List<ApplicationDTO>> showAllApplicationJobSeekerId(@RequestHeader Long userId){
        List<ApplicationDTO> applicationDTOS=applicationServiceImpl.showAllApplicationByUserId(userId);
        return new ResponseEntity<>(applicationDTOS, HttpStatus.OK);
    }
    @GetMapping("/all-by-post-id/{id}")
    public ResponseEntity<List<ApplicationDTO>> showAllApplicationByPostId(@PathVariable Long id){
        List<ApplicationDTO> applicationDTOS=applicationServiceImpl.showAllApplicationByPostId(id);
        return new ResponseEntity<>(applicationDTOS, HttpStatus.OK);
    }
    @GetMapping("/get-by-accepted")
    public ResponseEntity<List<ApplicationDTO>> findApplicationByAccepted(@RequestHeader Long userId){
        List<ApplicationDTO> applicationDTOS=applicationServiceImpl.findApplicationByAccepted(userId);
        return new ResponseEntity<>(applicationDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable(value = "id") Long id){
        ApplicationDTO applicationDTO = applicationServiceImpl.getApplicationById(id);
        return new ResponseEntity<>(applicationDTO, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<ApplicationDTO> addApplication(@RequestBody ApplicationDTO applicationDTO, @RequestHeader Long userId){
        applicationDTO.setJobSeekerId(userId);
        applicationDTO.setApplicationDate(LocalDate.now());
        applicationDTO.setApplicationStatus(AppStatus.PENDING);
        ApplicationDTO application=applicationServiceImpl.addApplication(applicationDTO);
        return new ResponseEntity<>(application, HttpStatus.CREATED);
    }
    @PutMapping("accept/{id}")
    public Map<String,AppStatus> acceptApplication(@PathVariable(value = "id") Long id){
        ApplicationDTO applicationDTO=applicationServiceImpl.getApplicationById(id);
        HashMap<String,AppStatus> response=new HashMap<>();
        if(applicationServiceImpl.acceptApplication(applicationDTO) == AppStatus.ACCEPTED){
            response.put("accept", AppStatus.ACCEPTED);
        }
        return response;
    }
    @PutMapping("reject/{id}")
    public HashMap<String,AppStatus> rejectApplication(@PathVariable(value = "id") Long id){
        ApplicationDTO applicationDTO=applicationServiceImpl.getApplicationById(id);
        HashMap<String,AppStatus> response=new HashMap<>();
        if(applicationServiceImpl.rejectApplication(applicationDTO) == AppStatus.REJECTED){
            response.put("reject", AppStatus.REJECTED);
        }
        return response;
    }
}
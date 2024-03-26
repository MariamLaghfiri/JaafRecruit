package org.example.education.controller;

import org.example.education.dto.EducationDTO;
import org.example.education.service.serviceImpl.EducationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/education")
public class EducationController {
    @Autowired
    EducationServiceImpl educationServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<List<EducationDTO>> showAllEducations(){
        List<EducationDTO> educationDTOS=educationServiceImpl.showAllEducations();
        return new ResponseEntity<>(educationDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EducationDTO> getEducationById(@PathVariable(value = "id") Long id){
        EducationDTO educationDTO = educationServiceImpl.getEducationById(id);
        return new ResponseEntity<>(educationDTO, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<EducationDTO> addEducation(@RequestBody EducationDTO educationDTO){
        EducationDTO education=educationServiceImpl.addEducation(educationDTO);
        return new ResponseEntity<>(education, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<EducationDTO> updateEducation(@RequestBody EducationDTO educationDTO, @PathVariable Long id){
        EducationDTO education=educationServiceImpl.updateEducation(id, educationDTO);
        return new ResponseEntity<>(education, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public HashMap<String,Boolean> deleteEducation(@PathVariable(value = "id") Long id){
        EducationDTO educationDTO=educationServiceImpl.getEducationById(id);
        HashMap<String,Boolean> response=new HashMap<>();
        if(educationServiceImpl.deleteEducation(educationDTO)){
            response.put("delete",Boolean.TRUE);
        }
        return response;
    }
}
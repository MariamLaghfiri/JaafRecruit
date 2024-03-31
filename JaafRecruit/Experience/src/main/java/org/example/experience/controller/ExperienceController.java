package org.example.experience.controller;

import org.example.experience.dto.ExperienceDTO;
import org.example.experience.service.serviceImpl.ExperienceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/experience")
public class ExperienceController {
    @Autowired
    ExperienceServiceImpl experienceServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<List<ExperienceDTO>> showAllExperiences(@RequestHeader Long userId){
        List<ExperienceDTO> experienceDTOS=experienceServiceImpl.showAllExperiences(userId);
        return new ResponseEntity<>(experienceDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDTO> getExperienceById(@PathVariable(value = "id") Long id){
        ExperienceDTO experienceDTO = experienceServiceImpl.getExperienceById(id);
        return new ResponseEntity<>(experienceDTO, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<ExperienceDTO> addExperience(@RequestBody ExperienceDTO experienceDTO, @RequestHeader Long userId){
        experienceDTO.setUserId(userId);
        ExperienceDTO experience=experienceServiceImpl.addExperience(experienceDTO);
        return new ResponseEntity<>(experience, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ExperienceDTO> updateExperience(@RequestBody ExperienceDTO experienceDTO, @PathVariable Long id, @RequestHeader Long userId){
        experienceDTO.setUserId(userId);
        ExperienceDTO experience=experienceServiceImpl.updateExperience(id, experienceDTO);
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public HashMap<String,Boolean> deleteExperience(@PathVariable(value = "id") Long id){
        ExperienceDTO experienceDTO=experienceServiceImpl.getExperienceById(id);
        HashMap<String,Boolean> response=new HashMap<>();
        if(experienceServiceImpl.deleteExperience(experienceDTO)){
            response.put("delete",Boolean.TRUE);
        }
        return response;
    }
}
package org.example.skills.controller;

import org.example.skills.dto.SkillsDTO;
import org.example.skills.entity.Enum.Category;
import org.example.skills.service.serviceImpl.SkillsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/skills")
public class SkillsController {
    @Autowired
    SkillsServiceImpl skillsServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<List<SkillsDTO>> showAllSkills(@RequestHeader Long userId){
        List<SkillsDTO> skillsDTOS=skillsServiceImpl.showAllSkills(userId);
        return new ResponseEntity<>(skillsDTOS, HttpStatus.OK);
    }
    @GetMapping("/get-by-category/{category}")
    public ResponseEntity<List<SkillsDTO>> findSkillsByCategory(@PathVariable(value = "category") Category category, @RequestHeader Long userId){
        List<SkillsDTO> skillsDTOS=skillsServiceImpl.findSkillsByCategory(category, userId);
        return new ResponseEntity<>(skillsDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SkillsDTO> getSkillsById(@PathVariable(value = "id") Long id){
        SkillsDTO skillsDTO = skillsServiceImpl.getSkillsById(id);
        return new ResponseEntity<>(skillsDTO, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<SkillsDTO> addSkills(@RequestBody SkillsDTO skillsDTO, @RequestHeader Long userId){
        skillsDTO.setUserId(userId);
        skillsDTO.setDeleted(false);
        SkillsDTO skills=skillsServiceImpl.addSkills(skillsDTO);
        return new ResponseEntity<>(skills, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<SkillsDTO> updateSkills(@RequestBody SkillsDTO skillsDTO, @PathVariable Long id, @RequestHeader String userId){
        System.out.println(userId);
        skillsDTO.setUserId(Long.valueOf(userId));
        skillsDTO.setDeleted(false);
        SkillsDTO skills=skillsServiceImpl.updateSkills(id, skillsDTO);
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public HashMap<String,Boolean> deleteSkills(@PathVariable(value = "id") Long id){
        SkillsDTO skillsDTO=skillsServiceImpl.getSkillsById(id);
        HashMap<String,Boolean> response=new HashMap<>();
        if(skillsServiceImpl.deleteSkills(skillsDTO)){
            response.put("delete",Boolean.TRUE);
        }
        return response;
    }
}
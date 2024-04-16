package org.example.company.controller;

import org.example.company.dto.CompanyDTO;
import org.example.company.service.serviceImpl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {
    @Autowired
    CompanyServiceImpl companyServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<List<CompanyDTO>> showAllCompanys(){
        List<CompanyDTO> companyDTOS=companyServiceImpl.showAllCompanys();
        return new ResponseEntity<>(companyDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable(value = "id") Long id){
        CompanyDTO companyDTO = companyServiceImpl.getCompanyById(id);
        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyDTO companyDTO , @RequestHeader Long userId){
        companyDTO.setUserId(userId);
        companyDTO.setDeleted(false);
        CompanyDTO company=companyServiceImpl.addCompany(companyDTO);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@RequestBody CompanyDTO companyDTO, @PathVariable Long id, @RequestHeader Long userId){
        companyDTO.setUserId(userId);
        companyDTO.setDeleted(false);
        CompanyDTO company=companyServiceImpl.updateCompany(id, companyDTO);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public HashMap<String,Boolean> deleteCompany(@PathVariable(value = "id") Long id){
        CompanyDTO companyDTO=companyServiceImpl.getCompanyById(id);
        HashMap<String,Boolean> response=new HashMap<>();
        if(companyServiceImpl.deleteCompany(companyDTO)){
            response.put("delete",Boolean.TRUE);
        }
        return response;
    }
}
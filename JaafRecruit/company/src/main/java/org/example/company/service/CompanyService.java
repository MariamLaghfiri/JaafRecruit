package org.example.company.service;

import org.example.company.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> showAllCompanys();
    CompanyDTO getCompanyById(Long id);
    CompanyDTO addCompany(CompanyDTO companyDTO);
    CompanyDTO updateCompany(Long id ,CompanyDTO companyDTO);
    Boolean deleteCompany(CompanyDTO companyDTO);
}

package org.example.company.service.serviceImpl;

import org.example.company.repository.CompanyRepository;
import org.example.company.service.CompanyService;
import org.example.company.dto.CompanyDTO;
import org.example.company.entity.Company;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<CompanyDTO> showAllCompanys() {
        List<Company> company = companyRepository.findByDeletedFalse();
        return company.stream()
                .map(s -> modelMapper.map(s,CompanyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        Company company= companyRepository.findByIdAndDeletedFalse(id).get();
        return modelMapper.map(company, CompanyDTO.class);
    }

    @Override
    public CompanyDTO addCompany(CompanyDTO companyDTO) {
        Company company = companyRepository.save(modelMapper.map(companyDTO,Company.class));
        return modelMapper.map(company,CompanyDTO.class);
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        companyDTO.setId(id);
        Company company = companyRepository.save(modelMapper.map(companyDTO, Company.class));
        return modelMapper.map(company,CompanyDTO.class);
    }

    @Override
    public Boolean deleteCompany(CompanyDTO companyDTO) {
        companyDTO.setDeleted(Boolean.TRUE);
        CompanyDTO companyDTO1=modelMapper.map(companyRepository.save(modelMapper.map(companyDTO, Company.class)),CompanyDTO.class);
        return companyDTO1.getDeleted();
    }
}

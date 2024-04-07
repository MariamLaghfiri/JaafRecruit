package org.example.company.service.serviceImpl;

import org.example.company.dto.CompanyDTO;
import org.example.company.entity.Company;
import org.example.company.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CompanyServiceImpl companyService;

    private CompanyDTO companyDTO;
    private Company company;

    @BeforeEach
    void setUp() {
        companyDTO = new CompanyDTO();
        companyDTO.setId(1L);
        companyDTO.setUserId(123L);
        companyDTO.setName("Test Company");
        companyDTO.setEmployeeNumber(100);
        companyDTO.setDescription("Test Description");
        companyDTO.setWebsite("www.example.com");
        companyDTO.setIndustry("Test Industry");
        companyDTO.setDeleted(false);

        company = new Company();
        company.setId(1L);
        company.setUserId(123L);
        company.setName("Test Company");
        company.setEmployeeNumber(100);
        company.setDescription("Test Description");
        company.setWebsite("www.example.com");
        company.setIndustry("Test Industry");
        company.setDeleted(false);
    }

    @Test
    void testShowAllCompanies() {
        // Mocking repository behavior
        when(companyRepository.findByDeletedFalse()).thenReturn(Arrays.asList(company));

        // Mocking model mapper behavior
        when(modelMapper.map(any(), eq(CompanyDTO.class))).thenReturn(companyDTO);

        // Calling the method to test
        List<CompanyDTO> result = companyService.showAllCompanys();

        // Verifying the result
        assertEquals(1, result.size());
        assertEquals(companyDTO, result.get(0));
    }

    @Test
    void testGetCompanyById() {
        // Mocking repository behavior
        when(companyRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(company));

        // Mocking model mapper behavior
        when(modelMapper.map(any(), eq(CompanyDTO.class))).thenReturn(companyDTO);

        // Calling the method to test
        CompanyDTO result = companyService.getCompanyById(1L);

        // Verifying the result
        assertEquals(companyDTO, result);
    }

    @Test
    void testAddCompany() {
        when(modelMapper.map(companyDTO, Company.class)).thenReturn(company);
        when(companyRepository.save(company)).thenReturn(company);
        when(modelMapper.map(company, CompanyDTO.class)).thenReturn(companyDTO);

        CompanyDTO result = companyService.addCompany(companyDTO);

        assertNotNull(result);
        assertEquals(companyDTO, result);
    }

    @Test
    void testUpdateCompany() {
        when(modelMapper.map(companyDTO, Company.class)).thenReturn(company);
        when(companyRepository.save(company)).thenReturn(company);
        when(modelMapper.map(company, CompanyDTO.class)).thenReturn(companyDTO);

        CompanyDTO result = companyService.updateCompany(1L, companyDTO);

        assertNotNull(result);
        assertEquals(companyDTO, result);
    }

    @Test
    void testDeleteCompany() {
        when(modelMapper.map(companyDTO, Company.class)).thenReturn(company);
        when(companyRepository.save(company)).thenReturn(company);
        when(modelMapper.map(company, CompanyDTO.class)).thenReturn(companyDTO);

        assertFalse(companyDTO.getDeleted());

        Boolean result = companyService.deleteCompany(companyDTO);

        assertTrue(result);
    }

}

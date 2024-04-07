package org.example.education.service.serviceImpl;

import org.example.education.dto.EducationDTO;
import org.example.education.entity.Education;
import org.example.education.repository.EducationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EducationServiceImplTest {

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EducationServiceImpl educationService;

    private EducationDTO mockEducationDTO;
    private Education mockEducation;

    @BeforeEach
    void setUp() {
        mockEducationDTO = new EducationDTO();
        mockEducationDTO.setId(1L);
        mockEducationDTO.setUserId(123L);
        mockEducationDTO.setDegree("Bachelor's");
        mockEducationDTO.setInstitution("University");
        mockEducationDTO.setGraduationYear(LocalDate.of(2020, 6, 30));
        mockEducationDTO.setDeleted(false);

        mockEducation = new Education();
        mockEducation.setId(1L);
        mockEducation.setUserId(123L);
        mockEducation.setDegree("Bachelor's");
        mockEducation.setInstitution("University");
        mockEducation.setGraduationYear(LocalDate.of(2020, 6, 30));
        mockEducation.setDeleted(false);
    }

    @Test
    void testShowAllEducations() {
        List<Education> mockEducationList = new ArrayList<>();
        mockEducationList.add(mockEducation);

        when(educationRepository.findByDeletedFalseAndUserId(123L)).thenReturn(mockEducationList);
        when(modelMapper.map(any(), eq(EducationDTO.class))).thenReturn(mockEducationDTO);

        List<EducationDTO> result = educationService.showAllEducations(123L);

        assertEquals(1, result.size());
        assertEquals(mockEducationDTO, result.get(0));
    }

    @Test
    void testGetEducationById() {
        when(educationRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(mockEducation));
        when(modelMapper.map(mockEducation, EducationDTO.class)).thenReturn(mockEducationDTO);

        EducationDTO result = educationService.getEducationById(1L);

        assertEquals(mockEducationDTO, result);
    }

    @Test
    void testAddEducation() {
        when(modelMapper.map(mockEducationDTO, Education.class)).thenReturn(mockEducation);
        when(educationRepository.save(mockEducation)).thenReturn(mockEducation);
        when(modelMapper.map(mockEducation, EducationDTO.class)).thenReturn(mockEducationDTO);

        EducationDTO result = educationService.addEducation(mockEducationDTO);

        assertEquals(mockEducationDTO, result);
    }

    @Test
    void testUpdateEducation() {
        when(modelMapper.map(mockEducationDTO, Education.class)).thenReturn(mockEducation);
        when(educationRepository.save(mockEducation)).thenReturn(mockEducation);
        when(modelMapper.map(mockEducation, EducationDTO.class)).thenReturn(mockEducationDTO);

        EducationDTO result = educationService.updateEducation(1L, mockEducationDTO);

        assertEquals(mockEducationDTO, result);
    }

    @Test
    void testDeleteEducation() {
        mockEducationDTO.setDeleted(true);
        when(modelMapper.map(mockEducationDTO, Education.class)).thenReturn(mockEducation);
        when(educationRepository.save(mockEducation)).thenReturn(mockEducation);
        when(modelMapper.map(mockEducation, EducationDTO.class)).thenReturn(mockEducationDTO);

        Boolean result = educationService.deleteEducation(mockEducationDTO);

        assertEquals(true, result);
    }
}


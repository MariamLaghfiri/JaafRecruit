package org.example.experience.service.serviceImpl;

import org.example.experience.dto.ExperienceDTO;
import org.example.experience.entity.Experience;
import org.example.experience.repository.ExperienceRepository;
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
class ExperienceServiceImplTest {

    @Mock
    private ExperienceRepository experienceRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ExperienceServiceImpl experienceService;

    private ExperienceDTO mockExperienceDTO;
    private Experience mockExperience;

    @BeforeEach
    void setUp() {
        mockExperienceDTO = new ExperienceDTO();
        mockExperienceDTO.setId(1L);
        mockExperienceDTO.setUserId(123L);
        mockExperienceDTO.setCompanyName("Example Company");
        mockExperienceDTO.setJobTitle("Software Engineer");
        mockExperienceDTO.setDescription("Worked on various projects");
        mockExperienceDTO.setStartDate(LocalDate.of(2018, 1, 1));
        mockExperienceDTO.setEndDate(LocalDate.of(2020, 12, 31));
        mockExperienceDTO.setDeleted(false);

        mockExperience = new Experience();
        mockExperience.setId(1L);
        mockExperience.setUserId(123L);
        mockExperience.setCompanyName("Example Company");
        mockExperience.setJobTitle("Software Engineer");
        mockExperience.setDescription("Worked on various projects");
        mockExperience.setStartDate(LocalDate.of(2018, 1, 1));
        mockExperience.setEndDate(LocalDate.of(2020, 12, 31));
        mockExperience.setDeleted(false);
    }

    @Test
    void testShowAllExperiences() {
        List<Experience> mockExperienceList = new ArrayList<>();
        mockExperienceList.add(mockExperience);

        when(experienceRepository.findByDeletedFalseAndUserId(123L)).thenReturn(mockExperienceList);
        when(modelMapper.map(any(), eq(ExperienceDTO.class))).thenReturn(mockExperienceDTO);

        List<ExperienceDTO> result = experienceService.showAllExperiences(123L);

        assertEquals(1, result.size());
        assertEquals(mockExperienceDTO, result.get(0));
    }

    @Test
    void testGetExperienceById() {
        when(experienceRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(mockExperience));
        when(modelMapper.map(mockExperience, ExperienceDTO.class)).thenReturn(mockExperienceDTO);

        ExperienceDTO result = experienceService.getExperienceById(1L);

        assertEquals(mockExperienceDTO, result);
    }

    @Test
    void testAddExperience() {
        when(modelMapper.map(mockExperienceDTO, Experience.class)).thenReturn(mockExperience);
        when(experienceRepository.save(mockExperience)).thenReturn(mockExperience);
        when(modelMapper.map(mockExperience, ExperienceDTO.class)).thenReturn(mockExperienceDTO);

        ExperienceDTO result = experienceService.addExperience(mockExperienceDTO);

        assertEquals(mockExperienceDTO, result);
    }

    @Test
    void testUpdateExperience() {
        when(modelMapper.map(mockExperienceDTO, Experience.class)).thenReturn(mockExperience);
        when(experienceRepository.save(mockExperience)).thenReturn(mockExperience);
        when(modelMapper.map(mockExperience, ExperienceDTO.class)).thenReturn(mockExperienceDTO);

        ExperienceDTO result = experienceService.updateExperience(1L, mockExperienceDTO);

        assertEquals(mockExperienceDTO, result);
    }

    @Test
    void testDeleteExperience() {
        mockExperienceDTO.setDeleted(true);
        when(modelMapper.map(mockExperienceDTO, Experience.class)).thenReturn(mockExperience);
        when(experienceRepository.save(mockExperience)).thenReturn(mockExperience);
        when(modelMapper.map(mockExperience, ExperienceDTO.class)).thenReturn(mockExperienceDTO);

        Boolean result = experienceService.deleteExperience(mockExperienceDTO);

        assertEquals(true, result);
    }
}

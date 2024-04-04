package org.example.skills.service.serviceImpl;

import org.example.skills.dto.SkillsDTO;
import org.example.skills.entity.Enum.Category;
import org.example.skills.entity.Skills;
import org.example.skills.repository.SkillsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillsServiceImplTest {

    @Mock
    private SkillsRepository skillsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SkillsServiceImpl skillsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Test Show All Skills :")
    @Test
    void testShowAllSkills() {
        // Mock data
        Long userId = 1L;
        List<Skills> mockSkillsList = new ArrayList<>();
        mockSkillsList.add(new Skills());
        when(skillsRepository.findByDeletedFalseAndUserId(userId)).thenReturn(mockSkillsList);

        // Test
        List<SkillsDTO> result = skillsService.showAllSkills(userId);

        // Verify
        assertEquals(mockSkillsList.size(), result.size());
        verify(skillsRepository, times(1)).findByDeletedFalseAndUserId(userId);
    }

    @DisplayName("Test Find Skills By Category :")
    @Test
    void testFindSkillsByCategory() {
        // Mock data
        Long userId = 1L;
        Category category = Category.TECHNICAL;
        List<Skills> mockSkillsList = new ArrayList<>();
        mockSkillsList.add(new Skills());
        when(skillsRepository.findSkillsByCategoryAndDeletedFalseAndUserId(category, userId)).thenReturn(mockSkillsList);

        // Test
        List<SkillsDTO> result = skillsService.findSkillsByCategory(category, userId);

        // Verify
        assertEquals(mockSkillsList.size(), result.size());
        verify(skillsRepository, times(1)).findSkillsByCategoryAndDeletedFalseAndUserId(category, userId);
    }


    @DisplayName("Test Get Skills By Id :")
    @Test
    void testGetSkillsById() {
        // Mock data
        Long id = 1L;
        Skills mockSkills = new Skills();
        when(skillsRepository.findByIdAndDeletedFalse(id)).thenReturn(Optional.of(mockSkills));
        when(modelMapper.map(mockSkills, SkillsDTO.class)).thenReturn(new SkillsDTO());

        // Test
        SkillsDTO result = skillsService.getSkillsById(id);

        // Verify
        assertNotNull(result);
        verify(skillsRepository, times(1)).findByIdAndDeletedFalse(id);
        verify(modelMapper, times(1)).map(mockSkills, SkillsDTO.class);
    }

    @DisplayName("Test Add Skills :")
    @Test
    void testAddSkills() {
        // Mock data
        SkillsDTO skillsDTO = new SkillsDTO();
        Skills mockSkills = new Skills();
        when(skillsRepository.save(any())).thenReturn(mockSkills);
        when(modelMapper.map(mockSkills, SkillsDTO.class)).thenReturn(new SkillsDTO());

        // Test
        SkillsDTO result = skillsService.addSkills(skillsDTO);

        // Verify
        assertNotNull(result);
        verify(skillsRepository, times(1)).save(any());
        verify(modelMapper, times(1)).map(mockSkills, SkillsDTO.class);
    }

//    @DisplayName("Test Update Skills :")
//    @Test
//    void testUpdateSkills() {
//        // Mock data
//        Long id = 1L;
//        SkillsDTO skillsDTO = new SkillsDTO();
//        skillsDTO.setId(id);
//        Skills mockSkills = new Skills();
//        when(skillsRepository.save(any())).thenReturn(mockSkills);
//        when(modelMapper.map(mockSkills, SkillsDTO.class)).thenReturn(new SkillsDTO());
//
//        // Test
//        SkillsDTO result = skillsService.updateSkills(id, skillsDTO);
//
//        // Verify
//        assertNotNull(result);
//        assertEquals(id, result.getId());
//        verify(skillsRepository, times(1)).save(any());
//        verify(modelMapper, times(1)).map(mockSkills, SkillsDTO.class);
//    }
//
//    @DisplayName("Test Delete Skills :")
//    @Test
//    void testDeleteSkills() {
//        // Mock data
//        SkillsDTO skillsDTO = new SkillsDTO();
//        Skills mockSkills = new Skills();
//        when(skillsRepository.save(any())).thenReturn(mockSkills);
//        when(modelMapper.map(mockSkills, SkillsDTO.class)).thenReturn(new SkillsDTO());
//
//        // Test
//        Boolean result = skillsService.deleteSkills(skillsDTO);
//
//        // Verify
//        assertTrue(result);
//        verify(skillsRepository, times(1)).save(any());
//        verify(modelMapper, times(1)).map(mockSkills, SkillsDTO.class);
//    }
}
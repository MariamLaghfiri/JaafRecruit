package org.example.skills.service;

import org.example.skills.dto.SkillsDTO;
import org.example.skills.entity.Enum.Category;

import java.util.List;

public interface SkillsService {
    List<SkillsDTO> showAllSkillss();
    List<SkillsDTO> findSkillsByCategory(Category category);
    SkillsDTO getSkillsById(Long id);
    SkillsDTO addSkills(SkillsDTO skillsDTO);
    SkillsDTO updateSkills(Long id ,SkillsDTO skillsDTO);
    Boolean deleteSkills(SkillsDTO skillsDTO);
}

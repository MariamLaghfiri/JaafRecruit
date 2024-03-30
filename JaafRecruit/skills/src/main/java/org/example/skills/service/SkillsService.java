package org.example.skills.service;

import org.example.skills.dto.SkillsDTO;
import org.example.skills.entity.Enum.Category;

import java.util.List;

public interface SkillsService {

    List<SkillsDTO> showAllSkills(Long id_user);

    List<SkillsDTO> findSkillsByCategory(Category category, Long userId);
    SkillsDTO getSkillsById(Long id);
    SkillsDTO addSkills(SkillsDTO skillsDTO);
    SkillsDTO updateSkills(Long id ,SkillsDTO skillsDTO);
    Boolean deleteSkills(SkillsDTO skillsDTO);
}

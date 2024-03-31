package org.example.experience.service;

import org.example.experience.dto.ExperienceDTO;

import java.util.List;

public interface ExperienceService {
    List<ExperienceDTO> showAllExperiences(Long userId);
    ExperienceDTO getExperienceById(Long id);
    ExperienceDTO addExperience(ExperienceDTO experienceDTO);
    ExperienceDTO updateExperience(Long id ,ExperienceDTO experienceDTO);
    Boolean deleteExperience(ExperienceDTO experienceDTO);
}

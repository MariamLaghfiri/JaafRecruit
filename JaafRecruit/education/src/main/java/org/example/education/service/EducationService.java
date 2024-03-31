package org.example.education.service;

import org.example.education.dto.EducationDTO;

import java.util.List;

public interface EducationService {
    List<EducationDTO> showAllEducations(Long userId);
    EducationDTO getEducationById(Long id);
    EducationDTO addEducation(EducationDTO educationDTO);
    EducationDTO updateEducation(Long id ,EducationDTO educationDTO);
    Boolean deleteEducation(EducationDTO educationDTO);
}

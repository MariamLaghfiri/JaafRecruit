package org.example.experience.service.serviceImpl;

import org.example.experience.dto.ExperienceDTO;
import org.example.experience.entity.Experience;
import org.example.experience.repository.ExperienceRepository;
import org.example.experience.service.ExperienceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceServiceImpl implements ExperienceService {
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<ExperienceDTO> showAllExperiences(Long userId) {
        List<Experience> experience = experienceRepository.findByDeletedFalseAndUserId(userId);
        return experience.stream()
                .map(s -> modelMapper.map(s,ExperienceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExperienceDTO getExperienceById(Long id) {
        Experience experience= experienceRepository.findByIdAndDeletedFalse(id).get();
        return modelMapper.map(experience, ExperienceDTO.class);
    }

    @Override
    public ExperienceDTO addExperience(ExperienceDTO experienceDTO) {
        Experience experience = experienceRepository.save(modelMapper.map(experienceDTO,Experience.class));
        return modelMapper.map(experience,ExperienceDTO.class);
    }

    @Override
    public ExperienceDTO updateExperience(Long id, ExperienceDTO experienceDTO) {
        experienceDTO.setId(id);
        Experience experience = experienceRepository.save(modelMapper.map(experienceDTO, Experience.class));
        return modelMapper.map(experience,ExperienceDTO.class);
    }

    @Override
    public Boolean deleteExperience(ExperienceDTO experienceDTO) {
        experienceDTO.setDeleted(Boolean.TRUE);
        ExperienceDTO experienceDTO1=modelMapper.map(experienceRepository.save(modelMapper.map(experienceDTO, Experience.class)),ExperienceDTO.class);
        return experienceDTO1.getDeleted();
    }
}

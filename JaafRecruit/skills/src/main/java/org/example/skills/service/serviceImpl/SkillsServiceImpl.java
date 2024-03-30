package org.example.skills.service.serviceImpl;

import org.example.skills.dto.SkillsDTO;
import org.example.skills.entity.Enum.Category;
import org.example.skills.entity.Skills;
import org.example.skills.repository.SkillsRepository;
import org.example.skills.service.SkillsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillsServiceImpl implements SkillsService {
    @Autowired
    SkillsRepository skillsRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<SkillsDTO> showAllSkills(Long userId) {
        List<Skills> skills = skillsRepository.findByDeletedFalseAndUserId(userId);
        return skills.stream()
                .map(s -> modelMapper.map(s,SkillsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillsDTO> findSkillsByCategory(Category category, Long userId) {
        List<Skills> skills = skillsRepository.findSkillsByCategoryAndDeletedFalseAndUserId(category, userId);
        return skills.stream()
                .map(s -> modelMapper.map(s,SkillsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SkillsDTO getSkillsById(Long id) {
        Skills skills= skillsRepository.findByIdAndDeletedFalse(id).get();
        return modelMapper.map(skills, SkillsDTO.class);
    }

    @Override
    public SkillsDTO addSkills(SkillsDTO skillsDTO) {
        Skills skills = skillsRepository.save(modelMapper.map(skillsDTO,Skills.class));
        return modelMapper.map(skills,SkillsDTO.class);
    }

    @Override
    public SkillsDTO updateSkills(Long id, SkillsDTO skillsDTO) {
        skillsDTO.setId(id);
        Skills skills = skillsRepository.save(modelMapper.map(skillsDTO, Skills.class));
        return modelMapper.map(skills,SkillsDTO.class);
    }

    @Override
    public Boolean deleteSkills(SkillsDTO skillsDTO) {
        skillsDTO.setDeleted(Boolean.TRUE);
        SkillsDTO skillsDTO1=modelMapper.map(skillsRepository.save(modelMapper.map(skillsDTO, Skills.class)),SkillsDTO.class);
        return skillsDTO1.getDeleted();
    }
}

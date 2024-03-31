package org.example.education.service.serviceImpl;

import org.example.education.dto.EducationDTO;
import org.example.education.entity.Education;
import org.example.education.repository.EducationRepository;
import org.example.education.service.EducationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<EducationDTO> showAllEducations(Long userId) {
        List<Education> education = educationRepository.findByDeletedFalseAndUserId(userId);
        return education.stream()
                .map(s -> modelMapper.map(s,EducationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EducationDTO getEducationById(Long id) {
        Education education= educationRepository.findByIdAndDeletedFalse(id).get();
        return modelMapper.map(education, EducationDTO.class);
    }

    @Override
    public EducationDTO addEducation(EducationDTO educationDTO) {
        Education education = educationRepository.save(modelMapper.map(educationDTO,Education.class));
        return modelMapper.map(education,EducationDTO.class);
    }

    @Override
    public EducationDTO updateEducation(Long id, EducationDTO educationDTO) {
        educationDTO.setId(id);
        Education education = educationRepository.save(modelMapper.map(educationDTO, Education.class));
        return modelMapper.map(education,EducationDTO.class);
    }

    @Override
    public Boolean deleteEducation(EducationDTO educationDTO) {
        educationDTO.setDeleted(Boolean.TRUE);
        EducationDTO educationDTO1=modelMapper.map(educationRepository.save(modelMapper.map(educationDTO, Education.class)),EducationDTO.class);
        return educationDTO1.getDeleted();
    }
}

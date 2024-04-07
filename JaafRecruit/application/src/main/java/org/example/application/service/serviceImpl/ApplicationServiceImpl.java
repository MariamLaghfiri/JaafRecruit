package org.example.application.service.serviceImpl;

import org.example.application.dto.ApplicationDTO;
import org.example.application.entity.Enum.AppStatus;
import org.example.application.entity.Application;
import org.example.application.repository.ApplicationRepository;
import org.example.application.service.ApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<ApplicationDTO> showAllApplicationByUserId(Long jobSeekerId) {
        List<Application> application = applicationRepository.findApplicationByJobSeekerId(jobSeekerId);
        return application.stream()
                .map(s -> modelMapper.map(s,ApplicationDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<ApplicationDTO> showAllApplicationByPostId(Long postId) {
        List<Application> application = applicationRepository.findApplicationByJobPostingId(postId);
        return application.stream()
                .map(s -> modelMapper.map(s,ApplicationDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<ApplicationDTO> findApplicationByAccepted(Long jobSeekerId) {
        List<Application> application = applicationRepository.findByApplicationStatusAndJobSeekerId(AppStatus.ACCEPTED ,jobSeekerId);
        return application.stream()
                .map(s -> modelMapper.map(s,ApplicationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDTO getApplicationById(Long id) {
        Application application;
        application = applicationRepository.findById(id).get();
        return modelMapper.map(application, ApplicationDTO.class);
    }

    @Override
    public ApplicationDTO addApplication(ApplicationDTO applicationDTO) {
        Application application = applicationRepository.save(modelMapper.map(applicationDTO,Application.class));
        return modelMapper.map(application,ApplicationDTO.class);
    }
    @Override
    public AppStatus acceptApplication(ApplicationDTO applicationDTO) {
        applicationDTO.setApplicationStatus(AppStatus.ACCEPTED);
        ApplicationDTO applicationDTO1=modelMapper.map(applicationRepository.save(modelMapper.map(applicationDTO, Application.class)),ApplicationDTO.class);
        return applicationDTO1.getApplicationStatus();
    }
    @Override
    public AppStatus rejectApplication(ApplicationDTO applicationDTO) {
        applicationDTO.setApplicationStatus(AppStatus.REJECTED);
        ApplicationDTO applicationDTO1=modelMapper.map(applicationRepository.save(modelMapper.map(applicationDTO, Application.class)),ApplicationDTO.class);
        return applicationDTO1.getApplicationStatus();
    }
}
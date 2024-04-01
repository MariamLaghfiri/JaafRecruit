package org.example.jobseeker.service.serviceImpl;

import org.example.jobseeker.repository.JobSeekerRepository;
import org.example.jobseeker.service.JobSeekerService;
import org.example.jobseeker.dto.JobSeekerDTO;
import org.example.jobseeker.entity.Enum.Availability;
import org.example.jobseeker.entity.JobSeeker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {
    @Autowired
    JobSeekerRepository jobSeekerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<JobSeekerDTO> showAllJobSeeker() {
        List<JobSeeker> jobSeeker = jobSeekerRepository.findJobSeekerByDeletedFalse();
        return jobSeeker.stream()
                .map(s -> modelMapper.map(s,JobSeekerDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<JobSeekerDTO> findJobSeekerByAvailability(Availability availability) {
        List<JobSeeker> jobSeeker = jobSeekerRepository.findByAvailabilityAndDeletedFalse(availability);
        return jobSeeker.stream()
                .map(s -> modelMapper.map(s,JobSeekerDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public JobSeekerDTO getJobSeekerById(Long id) {
        JobSeeker jobSeeker= jobSeekerRepository.findByIdAndDeletedFalse(id).get();
        return modelMapper.map(jobSeeker, JobSeekerDTO.class);
    }
    @Override
    public JobSeekerDTO addJobSeeker(JobSeekerDTO jobSeekerDTO) {
        JobSeeker jobSeeker = jobSeekerRepository.save(modelMapper.map(jobSeekerDTO,JobSeeker.class));
        return modelMapper.map(jobSeeker,JobSeekerDTO.class);
    }
    @Override
    public JobSeekerDTO updateJobSeeker(Long id, JobSeekerDTO jobSeekerDTO) {
        jobSeekerDTO.setId(id);
        JobSeeker jobSeeker = jobSeekerRepository.save(modelMapper.map(jobSeekerDTO, JobSeeker.class));
        return modelMapper.map(jobSeeker,JobSeekerDTO.class);
    }
    @Override
    public Boolean deleteJobSeeker(JobSeekerDTO jobSeekerDTO) {
        jobSeekerDTO.setDeleted(Boolean.TRUE);
        JobSeekerDTO jobSeekerDTO1=modelMapper.map(jobSeekerRepository.save(modelMapper.map(jobSeekerDTO, JobSeeker.class)),JobSeekerDTO.class);
        return jobSeekerDTO1.getDeleted();
    }
}
package org.example.jobposting.service.serviceImpl;

import org.example.jobposting.dto.JobPostingDTO;
import org.example.jobposting.entity.Enum.Contract;
import org.example.jobposting.entity.Enum.JobType;
import org.example.jobposting.entity.JobPosting;
import org.example.jobposting.repository.JobPostingRepository;
import org.example.jobposting.service.JobPostingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPostingServiceImpl implements JobPostingService {
    @Autowired
    JobPostingRepository jobPostingRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<JobPostingDTO> showAllJobPosting() {
        List<JobPosting> jobPosting = jobPostingRepository.findAll();
        return jobPosting.stream()
                .map(s -> modelMapper.map(s,JobPostingDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<JobPostingDTO> findJobPostingByContract(Contract contract) {
        List<JobPosting> jobPosting = jobPostingRepository.findByContractAndCloseFalse(contract);
        return jobPosting.stream()
                .map(s -> modelMapper.map(s,JobPostingDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<JobPostingDTO> showAllAvailableJobPosting() {
        List<JobPosting> jobPosting = jobPostingRepository.findByCloseFalse();
        return jobPosting.stream()
                .map(s -> modelMapper.map(s,JobPostingDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<JobPostingDTO> showAllJobPostingByCompanyId(Long companyId) {
        List<JobPosting> jobPosting = jobPostingRepository.findByCompanyIdAndCloseFalse(companyId);
        return jobPosting.stream()
                .map(s -> modelMapper.map(s,JobPostingDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<JobPostingDTO> findJobPostingByJobType(JobType jobType) {
        List<JobPosting> jobPosting = jobPostingRepository.findByJobTypeAndCloseFalse(jobType);
        return jobPosting.stream()
                .map(s -> modelMapper.map(s,JobPostingDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public JobPostingDTO getJobPostingById(Long id) {
        JobPosting jobPosting= jobPostingRepository.findByIdAndCloseFalse(id).get();
        return modelMapper.map(jobPosting, JobPostingDTO.class);
    }
    @Override
    public JobPostingDTO addJobPosting(JobPostingDTO jobPostingDTO) {
        JobPosting jobPosting = jobPostingRepository.save(modelMapper.map(jobPostingDTO,JobPosting.class));
        return modelMapper.map(jobPosting,JobPostingDTO.class);
    }
    @Override
    public JobPostingDTO updateJobPosting(Long id, JobPostingDTO jobPostingDTO) {
        jobPostingDTO.setId(id);
        JobPosting jobPosting = jobPostingRepository.save(modelMapper.map(jobPostingDTO, JobPosting.class));
        return modelMapper.map(jobPosting,JobPostingDTO.class);
    }
    @Override
    public Boolean closeJobPosting(JobPostingDTO jobPostingDTO) {
        jobPostingDTO.setClose(Boolean.TRUE);
        JobPostingDTO jobPostingDTO1=modelMapper.map(jobPostingRepository.save(modelMapper.map(jobPostingDTO, JobPosting.class)),JobPostingDTO.class);
        return jobPostingDTO1.getClose();
    }
    @Override
    @Scheduled(fixedRate = 86400000) // Executes once a day
    public void closeJobPostingsWithPastClosingDates() {
        List<JobPosting> jobPostings = jobPostingRepository.findAllByClosingDateBeforeAndCloseFalse(LocalDate.now());
        for (JobPosting jobPosting : jobPostings) {
            jobPosting.setClose(true);
            jobPostingRepository.save(jobPosting);
        }
    }
}
package org.example.jobposting.service;

import org.example.jobposting.dto.JobPostingDTO;
import org.example.jobposting.entity.Enum.Contract;
import org.example.jobposting.entity.Enum.JobType;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface JobPostingService {

    List<JobPostingDTO> showAllAvailableJobPosting();
    List<JobPostingDTO> showAllJobPosting();
    List<JobPostingDTO> showAllJobPostingByCompanyId(Long companyId);

    List<JobPostingDTO> findJobPostingByContract(Contract contract);
    List<JobPostingDTO> findJobPostingByJobType(JobType jobType);
    JobPostingDTO getJobPostingById(Long id);
    JobPostingDTO addJobPosting(JobPostingDTO jobPostingDTO);
    JobPostingDTO updateJobPosting(Long id ,JobPostingDTO jobPostingDTO);
    Boolean closeJobPosting(JobPostingDTO jobPostingDTO);
    @Scheduled(fixedRate = 86400000) // Executes once a day
    void closeJobPostingsWithPastClosingDates();
}
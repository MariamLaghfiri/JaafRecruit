package org.example.jobseeker.service;

import org.example.jobseeker.dto.JobSeekerDTO;
import org.example.jobseeker.entity.Enum.Availability;

import java.util.List;

public interface JobSeekerService {

    List<JobSeekerDTO> showAllJobSeeker();

    List<JobSeekerDTO> findJobSeekerByAvailability(Availability category);
    JobSeekerDTO getJobSeekerById(Long id);
    JobSeekerDTO addJobSeeker(JobSeekerDTO jobSeekerDTO);
    JobSeekerDTO updateJobSeeker(Long id ,JobSeekerDTO jobSeekerDTO);
    Boolean deleteJobSeeker(JobSeekerDTO jobSeekerDTO);
}

package org.example.jobposting.repository;

import org.example.jobposting.entity.Enum.Contract;
import org.example.jobposting.entity.Enum.JobType;
import org.example.jobposting.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting,Long> {
    List<JobPosting> findByJobTypeAndCloseFalse(JobType jobType);
    List<JobPosting> findByContractAndCloseFalse(Contract contract);

    Optional<JobPosting> findByIdAndCloseFalse(Long id);

    List<JobPosting> findAllByClosingDateBeforeAndCloseFalse(LocalDate date);

    List<JobPosting> findByCloseFalse();
    List<JobPosting> findByCompanyIdAndCloseFalse(Long companyId);

}
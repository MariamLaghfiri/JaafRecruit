package org.example.jobseeker.repository;

import org.example.jobseeker.entity.Enum.Availability;
import org.example.jobseeker.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker,Long> {
    List<JobSeeker> findByAvailabilityAndDeletedFalse(Availability availability);

    List<JobSeeker> findJobSeekerByDeletedFalse();

    Optional<JobSeeker> findByIdAndDeletedFalse(Long id);
}
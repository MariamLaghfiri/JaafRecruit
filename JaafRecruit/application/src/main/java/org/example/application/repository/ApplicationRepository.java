package org.example.application.repository;

import org.example.application.entity.Application;
import org.example.application.entity.Enum.AppStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {
    List<Application> findByApplicationStatusAndJobSeekerId(AppStatus appStatus,Long jobSeekerId);
    List<Application> findApplicationByJobSeekerId(Long jobSeekerId);
    List<Application> findApplicationByJobPostingId(Long jobPostingId);
}
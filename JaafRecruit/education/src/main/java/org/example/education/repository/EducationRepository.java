package org.example.education.repository;

import org.example.education.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education,Long> {
    List<Education> findByDeletedFalseAndUserId(Long userId);
    Optional<Education> findByIdAndDeletedFalse(Long id);
}
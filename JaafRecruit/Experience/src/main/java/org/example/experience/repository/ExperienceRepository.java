package org.example.experience.repository;

import org.example.experience.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience,Long> {
    List<Experience> findByDeletedFalseAndUserId(Long userId);
    Optional<Experience> findByIdAndDeletedFalse(Long id);
}
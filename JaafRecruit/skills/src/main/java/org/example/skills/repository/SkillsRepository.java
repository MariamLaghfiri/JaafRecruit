package org.example.skills.repository;

import org.example.skills.entity.Enum.Category;
import org.example.skills.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillsRepository extends JpaRepository<Skills,Long> {
    List<Skills> findByDeletedFalse();
    List<Skills> findSkillsByCategoryAndDeletedFalse(Category category);
    Optional<Skills> findByIdAndDeletedFalse(Long id);
}
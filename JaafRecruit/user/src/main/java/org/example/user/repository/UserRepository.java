package org.example.user.repository;

import org.example.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByDeletedFalse();
    Optional<User> findByIdAndDeletedFalse(Long id);
}
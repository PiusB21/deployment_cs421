package com.example.deployment_task.Repo;

import com.example.deployment_task.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findBySubjectCode(String regNo);

}

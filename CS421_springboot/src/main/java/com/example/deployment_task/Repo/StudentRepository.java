package com.example.deployment_task.Repo;

import com.example.deployment_task.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByRegNo(String regNo);
}

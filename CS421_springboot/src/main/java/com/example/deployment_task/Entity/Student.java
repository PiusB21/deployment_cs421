package com.example.deployment_task.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String regNo;
    private String program;
}


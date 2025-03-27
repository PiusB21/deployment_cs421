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
@Table(name="subjects")
public class Subject {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String subjectCode;
    private int academicYear;
    private int semester;
}

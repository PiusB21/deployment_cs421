package com.example.deployment_task.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private String name;
    private String subjectCode;
    private int academicYear;
    private int semester;
}

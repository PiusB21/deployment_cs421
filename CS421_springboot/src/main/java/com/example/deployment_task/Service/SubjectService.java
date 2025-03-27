package com.example.deployment_task.Service;

import com.example.deployment_task.DTO.SubjectDTO;
import com.example.deployment_task.Entity.Subject;
import com.example.deployment_task.Repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject registerSubject(@RequestBody SubjectDTO subjectDTO) throws Exception{
        if(subjectRepository.findBySubjectCode(subjectDTO.getSubjectCode()).isPresent()){
            throw new Exception("Subject already exists");
        }
        var subject = Subject.builder()
                .subjectCode(subjectDTO.getSubjectCode())
                .name(subjectDTO.getName())
                .academicYear(subjectDTO.getAcademicYear())
                .semester(subjectDTO.getSemester())
                .build();

        return subjectRepository.save(subject);
    }

    public List<Subject> registerSubjects(@RequestBody List<SubjectDTO> subjectDTOList) throws Exception{
        List<Subject> subjects = new ArrayList<>();

        for(SubjectDTO subjectDTO : subjectDTOList){
            Subject subject = registerSubject(subjectDTO);
            subjects.add(subject);
        }
        return subjects;
    }

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(int id) throws Exception{
        if(subjectRepository.findById(id).isPresent()){
            return subjectRepository.findById(id).get();
        }
        else throw new Exception("Subject not found");
    }
}

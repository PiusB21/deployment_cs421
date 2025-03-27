package com.example.deployment_task.Controller;

import com.example.deployment_task.DTO.StudentDTO;
import com.example.deployment_task.DTO.SubjectDTO;
import com.example.deployment_task.Entity.Student;
import com.example.deployment_task.Entity.Subject;
import com.example.deployment_task.Service.StudentService;
import com.example.deployment_task.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SubjectDTO subjectDTO) {
        try{
            return ResponseEntity.ok(subjectService.registerSubject(subjectDTO));
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/register-many")
    public ResponseEntity<?> registerMany(@RequestBody List<SubjectDTO> subjectDTOList) {
        try { return ResponseEntity.ok(subjectService.registerSubjects(subjectDTOList)); }
        catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("subject")
    public ResponseEntity<?> getSubject(int id) {
        try{return ResponseEntity.ok(subjectService.getSubjectById(id)); }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

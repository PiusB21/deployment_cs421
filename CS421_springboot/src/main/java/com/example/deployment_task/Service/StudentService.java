package com.example.deployment_task.Service;

import com.example.deployment_task.DTO.StudentDTO;
import com.example.deployment_task.Entity.Student;
import com.example.deployment_task.Repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student registerStudent(@RequestBody StudentDTO studentDTO) throws Exception{
        if(studentRepository.findByRegNo(studentDTO.getRegNo()).isPresent()){
            throw new Exception("Student already exists");
        }
        var student = Student.builder()
                .email(studentDTO.getEmail())
                .regNo(studentDTO.getRegNo())
                .first_name(studentDTO.getFirst_name())
                .last_name(studentDTO.getLast_name())
                .program(studentDTO.getProgram())
                .build();

        return studentRepository.save(student);
    }

    public List<Student> registerStudents(@RequestBody List<StudentDTO> studentDTOList) throws Exception{
        List<Student> students = new ArrayList<>();

        for(StudentDTO studentDTO : studentDTOList){
          Student student = registerStudent(studentDTO);
          students.add(student);
        }
        return students;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) throws Exception{
        if(studentRepository.findById(id).isPresent()){
            return studentRepository.findById(id).get();
        }
        else throw new Exception("Student not found");
    }
}

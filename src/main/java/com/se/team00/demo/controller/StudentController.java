package com.se.team00.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.se.team00.demo.entity.StudentEntity;
import com.se.team00.demo.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student/{fname}/{lname}/{studentId}")
    public StudentEntity addStudent(@PathVariable String fname, @PathVariable
            String lname, @PathVariable String studentId) {
        StudentEntity s = new StudentEntity();
        s.setFirstName(fname);
        s.setLastName(lname);
        s.setStudentId(studentId);
        return studentRepository.save(s);
    }

    @GetMapping("/students")
    public Collection<StudentEntity> getAllStudent() {
        return
                studentRepository.findAll().stream().collect(Collectors.toList());
    }
}
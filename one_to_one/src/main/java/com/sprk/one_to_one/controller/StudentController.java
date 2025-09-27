package com.sprk.one_to_one.controller;

import com.sprk.one_to_one.entity.Student;
import com.sprk.one_to_one.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    @PostMapping("/student/add")
    public Student addStudent(@RequestBody Student student) {
        // Student -> Details
        // When We have details it should automatically saved in studentDetail table

        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}

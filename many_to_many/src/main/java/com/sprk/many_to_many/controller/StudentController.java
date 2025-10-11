package com.sprk.many_to_many.controller;

import com.sprk.many_to_many.entity.Student;
import com.sprk.many_to_many.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {


    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student) {
        student.setCoursesEnrolled(null);
        System.out.println(student);

        Student dbStudent = studentRepository.save(student);

        return dbStudent;
    }
}

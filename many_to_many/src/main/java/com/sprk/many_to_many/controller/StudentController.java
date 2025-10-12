package com.sprk.many_to_many.controller;

import com.sprk.many_to_many.entity.Student;
import com.sprk.many_to_many.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/show/{rollNo}")
    public Student showStudent(@PathVariable int rollNo) {
        return studentRepository.findById(rollNo).orElseThrow(()->new RuntimeException("Student Not Found"));
    }
    @GetMapping("/show-all")
    public List<Student> showAllStudent() {
        return studentRepository.findAll();
    }
}

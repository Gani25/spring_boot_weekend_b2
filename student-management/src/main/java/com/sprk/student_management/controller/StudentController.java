package com.sprk.student_management.controller;

import com.sprk.student_management.entity.Student;
import com.sprk.student_management.service.StudentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    public Student saveStudent(@RequestBody Student student) {

        Student savedStudent = studentService.saveStudent(student);

        return savedStudent;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{rollNo}")
    public Student getStudent(@PathVariable long rollNo) {

        return studentService.getStudentByRollNo(rollNo);

    }

}

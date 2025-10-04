package com.sprk.one_to_one.controller;

import com.sprk.one_to_one.entity.Student;
import com.sprk.one_to_one.entity.StudentDetail;
import com.sprk.one_to_one.repository.StudentDetailRepository;
import com.sprk.one_to_one.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentDetailController {

    private final StudentRepository studentRepository;
    private final StudentDetailRepository studentDetailRepository;

    @PostMapping("/student/detail/{rollNo}")
    public Student saveStudentDetail(@PathVariable int rollNo, @RequestBody StudentDetail studentDetail) {

        Student student = studentRepository
                .findById(rollNo)
                .orElseThrow(()->new RuntimeException("Student Not Found"));

        student.setStudentDetail(studentDetail);

        return studentRepository.save(student);
    }

    @GetMapping("/get-details")
    public List<StudentDetail> getStudentDetails() {
        return studentDetailRepository.findAll();
    }

    @DeleteMapping("/student/detail/remove/{studentDetailId}")
    public String deleteStudentDetail(@PathVariable int studentDetailId) {
        StudentDetail studentDetail = studentDetailRepository
                .findById(studentDetailId)
                .orElseThrow(()->new RuntimeException("Student Detail Not Found"));

        Student student = studentDetail.getStudent();
        student.setStudentDetail(null);

        studentRepository.save(student); // update

        return "Student Detail Deleted";
    }

    // update student detail by student_detial_id, updated studentDetail Obj




}

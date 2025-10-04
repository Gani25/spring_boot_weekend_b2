package com.sprk.one_to_one.controller;

import com.sprk.one_to_one.entity.Student;
import com.sprk.one_to_one.entity.StudentDetail;
import com.sprk.one_to_one.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    // Update Base Entity
    @PutMapping("/student/{rollNo}")
    public Student updateStudent(@PathVariable int rollNo, @RequestBody Student updatedStudent) {

        Student student = studentRepository
                .findById(rollNo)
                .orElseThrow(()->new RuntimeException("Student Not Found"));

        student.setName(updatedStudent.getName());
        student.setGender(updatedStudent.getGender());
        student.setAge(updatedStudent.getAge());
        student.setPhone(updatedStudent.getPhone());
//        student.setStudentDetail(updatedStudent.getStudentDetail()); WRONG -> Insert new Detail
        StudentDetail studentDetail =  student.getStudentDetail();
        studentDetail.setEducation(updatedStudent.getStudentDetail().getEducation());
        studentDetail.setHobby(updatedStudent.getStudentDetail().getHobby());

        student.setStudentDetail(studentDetail);
        return studentRepository.save(student);
    }

    @DeleteMapping("/student/remove/{rollNo}")
    public String removeStudent(@PathVariable int rollNo) {
        Student student = studentRepository
                .findById(rollNo)
                .orElseThrow(()->new RuntimeException("Student Not Found"));
        studentRepository.delete(student);
        return "Student Removed Successfully";
    }
}

package com.sprk.student_management.controller;

import com.sprk.student_management.dto.StudentDto;
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
    public Student saveStudent(@RequestBody StudentDto studentDto) {

        Student savedStudent = studentService.saveStudent(studentDto);

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

    @GetMapping("/student/gender/{gender}")
    public List<Student> getStudentsByGender(@PathVariable String gender) {
        return studentService.getStudentsByGender(gender);
    }

    @DeleteMapping("/student/{rollNo}")
    public String deleteStudent(@PathVariable long rollNo) {
        if(studentService.deleteStudentByRollNo(rollNo)){
            return String.format("Student with roll no = %d, deleted successfully",rollNo);
        }else {
            String msg = "Student with roll no = "+rollNo+" not found";
            return msg;
        }
    }

    // Request Param
    @PutMapping("/student/{rollNo}")
    public Student updateStudent(@RequestBody Student updatedStudent, @PathVariable long rollNo) {

        return studentService.updateByRollNo(rollNo, updatedStudent);

    }

}

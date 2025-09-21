package com.sprk.student_management.service;

import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    StudentDto saveStudent(StudentDto studentDto);

    List<StudentDto> getAllStudentDtos();

    StudentDto getStudentByRollNo(String rollNo);

    List<Student> getStudentsByGender(String gender);

    boolean deleteStudentByRollNo(long rollNo);

    StudentDto updateByRollNo(long rollNo, StudentDto updatedStudent);
}

package com.sprk.student_management.service;

import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    Student saveStudent(StudentDto studentDto);

    List<Student> getAllStudents();

    Student getStudentByRollNo(long rollNo);

    List<Student> getStudentsByGender(String gender);

    boolean deleteStudentByRollNo(long rollNo);

    Student updateByRollNo(long rollNo, Student updatedStudent);
}

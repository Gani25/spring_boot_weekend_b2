package com.sprk.student_management.service;

import com.sprk.student_management.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentByRollNo(long rollNo);

    List<Student> getStudentsByGender(String gender);

    boolean deleteStudentByRollNo(long rollNo);

    Student updateByRollNo(long rollNo, Student updatedStudent);
}

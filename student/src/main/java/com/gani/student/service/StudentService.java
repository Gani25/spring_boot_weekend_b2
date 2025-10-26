package com.gani.student.service;

import com.gani.student.dto.StudentDto;
import com.gani.student.entity.Student;

import java.util.Optional;

public interface StudentService {

    Optional<Student> findByEmail(String email);
    Optional<Student> findByPhone(String phone);

    Student save(StudentDto studentDto);
}

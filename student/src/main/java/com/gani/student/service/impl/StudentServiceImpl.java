package com.gani.student.service.impl;

import com.gani.student.dto.StudentDto;
import com.gani.student.entity.Student;
import com.gani.student.repository.StudentRepository;
import com.gani.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);

    }

    @Override
    public Optional<Student> findByPhone(String phone) {

        return studentRepository.findByPhone(phone);
    }

    @Override
    public Student save(StudentDto studentDto) {

        Student student = Student.builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .email(studentDto.getEmail())
                .phone(studentDto.getPhone())
                .gender(studentDto.getGender())
                .rollNo(studentDto.getRollNo())
                .build();

        Student stud = studentRepository.save(student);
        return stud;
    }
}

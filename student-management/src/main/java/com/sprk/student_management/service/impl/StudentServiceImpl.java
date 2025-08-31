package com.sprk.student_management.service.impl;

import com.sprk.student_management.entity.Student;
import com.sprk.student_management.repository.StudentRepository;
import com.sprk.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        Student dbStudent =  studentRepository.save(student);
        return dbStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByRollNo(long rollNo) {
        return studentRepository.findById(rollNo).orElse(null);
    }
}

package com.sprk.student_management.service.impl;

import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;
import com.sprk.student_management.repository.StudentRepository;
import com.sprk.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student saveStudent(StudentDto studentDto) {
        // Convert dto to entity
        Student dbStudent = studentRepository.save(student);
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

    @Override
    public List<Student> getStudentsByGender(String gender) {
        return studentRepository.findByGender(gender);
    }

    @Override
    public boolean deleteStudentByRollNo(long rollNo) {
        Student existingStudent = studentRepository.findById(rollNo).orElse(null);
        if (existingStudent != null) {
//            studentRepository.delete(existingStudent);
            studentRepository.deleteById(rollNo);
            return true;
        }
        return false;
    }

    @Override
    public Student updateByRollNo(long rollNo, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(rollNo).orElse(null);
        if (existingStudent == null) {
            // no update return empty obj
            return null;
        }
        // update logic
        updatedStudent.setRollNo(rollNo); // Added rollno so it will update
        return studentRepository.save(updatedStudent);

    }
}

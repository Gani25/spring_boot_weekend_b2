package com.sprk.student_management.mapper;

import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    // 2 methods
    public Student mappedStudentDtoToStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setRollNo(studentDto.getRollNo());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setGender(studentDto.getGender());
        student.setAge(studentDto.getAge());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        return student;
    }

    public StudentDto mappedStudentToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setRollNo(student.getRollNo());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
        studentDto.setGender(student.getGender());
        studentDto.setAge(student.getAge());
        studentDto.setPhoneNumber(student.getPhoneNumber());
        return studentDto;
    }
}

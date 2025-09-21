package com.sprk.student_management.service.impl;

import com.sprk.student_management.constant.StudentApiConstant;
import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;
import com.sprk.student_management.exception.RollNumberIncorrectException;
import com.sprk.student_management.exception.StudentEmailAlreadyExists;
import com.sprk.student_management.exception.StudentException;
import com.sprk.student_management.exception.StudentNotFoundException;
import com.sprk.student_management.mapper.StudentMapper;
import com.sprk.student_management.repository.StudentRepository;
import com.sprk.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        // CHeck if Email Already available then dont save
        // Throw Exception
        Student studentByEmail = studentRepository.findByEmail(studentDto.getEmail());
        if (studentByEmail != null) {
            String msg = String.format("Student with email = %s already available kindly select another email", studentDto.getEmail());
            throw new StudentEmailAlreadyExists(msg, HttpStatus.BAD_REQUEST);
        }

        // Convert dto to entity

        Student student = studentMapper.mappedStudentDtoToStudent(studentDto);
        Student dbStudent = studentRepository.save(student);
        return studentMapper.mappedStudentToStudentDto(dbStudent);
    }

    @Override
    public List<StudentDto> getAllStudentDtos() {

        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = students
                .stream()
                .map((student) -> studentMapper.mappedStudentToStudentDto(student))
                .toList();
//        List<StudentDto> studentDtos1 = students
//                .stream()
//                .map(studentMapper::mappedStudentToStudentDto)
//                .toList();

        return studentDtos;
    }

    @Override
    public StudentDto getStudentByRollNo(String rollNoStr) {
        if(!rollNoStr.matches("^\\d+$")){
            throw new RollNumberIncorrectException(String.format(StudentApiConstant.INCORRECT_ROLL_NUMBER_FORMAT,rollNoStr),HttpStatus.valueOf(StudentApiConstant.BAD_REQUEST_STATUS));
        }

        // If not working means roll number only contains number
        long rollNo = Long.parseLong(rollNoStr);// type conversion
        Student student = studentRepository
                .findById(rollNo)
                .orElseThrow(()-> new StudentNotFoundException(String
                        .format("Student with rollno = %d not exists", rollNo),HttpStatus.valueOf(StudentApiConstant.BAD_REQUEST_STATUS))
                );

        StudentDto studentDto = studentMapper.mappedStudentToStudentDto(student);

        return studentDto;
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
    public StudentDto updateByRollNo(long rollNo, StudentDto updatedStudentDto) {
//        Student existingStudent = studentRepository.findById(rollNo).orElse(null);
        Student existingStudent = studentRepository
                .findById(rollNo)
                .orElseThrow(()->new StudentNotFoundException(String
                        .format("Student with rollno = %d not exists", rollNo),HttpStatus.NOT_FOUND)
                );
        // update logic
        Student updatedStudent = studentMapper.mappedStudentDtoToStudent(updatedStudentDto);
        updatedStudent.setRollNo(rollNo); // Added rollno so it will update
        Student student = studentRepository.save(updatedStudent);

        return studentMapper.mappedStudentToStudentDto(student);

    }
}

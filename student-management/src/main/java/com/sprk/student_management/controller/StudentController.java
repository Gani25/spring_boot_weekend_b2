package com.sprk.student_management.controller;

import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.dto.SuccessResponseDto;
import com.sprk.student_management.entity.Student;
import com.sprk.student_management.service.StudentService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<SuccessResponseDto<StudentDto>> saveStudent(@Valid @RequestBody StudentDto studentDto) {

        StudentDto savedStudentDto = studentService.saveStudent(studentDto);
        SuccessResponseDto<StudentDto> successResponseDto = new SuccessResponseDto<>();
        successResponseDto.setData(savedStudentDto);
        successResponseDto.setMessage("Student Saved Successfully");
        successResponseDto.setStatusCode(HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponseDto);
    }

    @GetMapping("/students")
    public  ResponseEntity<SuccessResponseDto<List<StudentDto>>> getAllStudents() {
        SuccessResponseDto<List<StudentDto>> successResponseDto = new SuccessResponseDto<>();
        List<StudentDto> allStudentDtos = studentService.getAllStudentDtos();
        successResponseDto.setData(allStudentDtos);
        successResponseDto.setStatusCode(HttpStatus.OK.value());
        successResponseDto.setMessage("All Students Fetch Successfully");

        return ResponseEntity.ok(successResponseDto);
    }

    @GetMapping("/student/{rollNo}")
    public ResponseEntity<SuccessResponseDto<StudentDto>> getStudent(@PathVariable long rollNo) {

//        return studentService.getStudentByRollNo(rollNo);
        return null;

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
    public ResponseEntity<SuccessResponseDto<StudentDto>> updateStudent(@Valid @RequestBody StudentDto updatedStudentDto, @PathVariable long rollNo) {

        StudentDto updatedStudentDto1 = studentService.updateByRollNo(rollNo,updatedStudentDto );

        SuccessResponseDto<StudentDto> successResponseDto = new SuccessResponseDto<>();
        successResponseDto.setData(updatedStudentDto1);
        successResponseDto.setStatusCode(HttpStatus.OK.value());
        successResponseDto.setMessage(String.format("Student with roll no = %d Updated Successfully",rollNo));

        return ResponseEntity.ok(successResponseDto);

    }

}

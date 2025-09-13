package com.sprk.student_management.controller.advice;

import com.sprk.student_management.dto.ErrorResponseDto;
import com.sprk.student_management.exception.StudentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class StudentControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto<String>> handleStudentException(StudentException studentException, WebRequest webRequest){
        ErrorResponseDto<String> errorResponseDto = new ErrorResponseDto<>();
        errorResponseDto.setMessage(studentException.getMessage());
        errorResponseDto.setStatus(studentException.getStatus());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        errorResponseDto.setApiPath(webRequest.getDescription(true));

        return ResponseEntity.status(studentException.getStatus()).body(errorResponseDto);

    }
}

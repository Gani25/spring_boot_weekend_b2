package com.sprk.student_management.exception;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends StudentException{

    public StudentNotFoundException(String message, HttpStatus status){
        super(message, status);
    }
}

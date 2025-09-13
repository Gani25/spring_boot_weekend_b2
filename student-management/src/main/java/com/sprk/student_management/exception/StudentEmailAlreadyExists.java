package com.sprk.student_management.exception;

import org.springframework.http.HttpStatus;

public class StudentEmailAlreadyExists extends StudentException{

    public StudentEmailAlreadyExists(String message, HttpStatus status){
        super(message, status);
    }
}

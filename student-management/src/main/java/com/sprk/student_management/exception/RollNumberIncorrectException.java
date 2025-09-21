package com.sprk.student_management.exception;

import org.springframework.http.HttpStatus;

public class RollNumberIncorrectException extends StudentException{

    public RollNumberIncorrectException(String message, HttpStatus status){
        super(message, status);
    }
}

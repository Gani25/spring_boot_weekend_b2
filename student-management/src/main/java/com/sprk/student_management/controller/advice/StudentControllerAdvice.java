package com.sprk.student_management.controller.advice;

import com.sprk.student_management.dto.ErrorResponseDto;
import com.sprk.student_management.exception.StudentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class StudentControllerAdvice extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // fetch all the error of validation and store in Key Value Pair -> Map
        Map<String, String> validationErrors = new HashMap<>();
        // BindingResult Object
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();

        // Loop over List allErrors and fetch all error field name and store their message
        for (ObjectError objectError : allErrors) {
            FieldError fieldError = ((FieldError)objectError);
            String fieldName = fieldError.getField();
            String errorMessage = objectError.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        }
        // Created a Map with all the failed fields
        ErrorResponseDto<Object> errorResponseDto = new ErrorResponseDto<>();
        errorResponseDto.setApiPath(request.getDescription(false));
        errorResponseDto.setTimestamp(LocalDateTime.now());
        errorResponseDto.setMessage(validationErrors);
        errorResponseDto.setStatus(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);

    }

    @ExceptionHandler(StudentException.class)
    public ResponseEntity<ErrorResponseDto<String>> handleStudentException(StudentException studentException, WebRequest webRequest){
        ErrorResponseDto<String> errorResponseDto = new ErrorResponseDto<>();
        errorResponseDto.setMessage(studentException.getMessage());
        errorResponseDto.setStatus(studentException.getStatus());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        errorResponseDto.setApiPath(webRequest.getDescription(true));

        return ResponseEntity.status(studentException.getStatus()).body(errorResponseDto);
    }
}

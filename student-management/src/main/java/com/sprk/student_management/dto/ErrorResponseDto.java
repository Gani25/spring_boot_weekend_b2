package com.sprk.student_management.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto<M> {

    private String apiPath;

    private HttpStatus status;

    private M message;

    private LocalDateTime timestamp;


}

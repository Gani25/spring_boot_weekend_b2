package com.sprk.student_management.dto;

import lombok.Data;

@Data
public class SuccessResponseDto<D> {

    private int statusCode;

    private String message;

    private D data;
}

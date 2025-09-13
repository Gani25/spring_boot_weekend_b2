package com.sprk.student_management.dto;

import lombok.Data;

@Data
public class SuccessResponseDto<Data> {

    private int statusCode;

    private String message;

    private Data data;
}

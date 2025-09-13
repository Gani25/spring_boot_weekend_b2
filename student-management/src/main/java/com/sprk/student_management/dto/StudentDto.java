package com.sprk.student_management.dto;

// This will be connected to client so we will do validation on DTO

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentDto {

    private long rollNo;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Size(min = 1, message = "Age cannot be negative or 0")
    private int age;

    private String gender;

    @Email(message = "Enter valid email address")
    @NotBlank(message = "email cannot be empty")
    private String email;

    @Pattern(regexp = "^\\d{8,15}$", message = "Please enter phone number in correct format")
    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;


}

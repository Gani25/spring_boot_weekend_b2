package com.gani.student.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data @ToString @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class StudentDto {

    private int rollNo;

    @NotBlank(message = "First Name Cannot Be Empty")
    private String firstName;

    @NotBlank(message = "Last Name Cannot Be Empty")
    private String lastName;

    @NotBlank(message = "Email Cannot Be Empty")
    @Email(message = "Enter correct email")
    private String email;

    @NotBlank(message = "Phone Cannot Be Empty")
    @Pattern(
            regexp = "^(\\+\\d{1,3}[- ]?)?(\\d{4} ?\\d{4} ?\\d{2}|\\d{5} ?\\d{5}|\\d{10,15})$",
            message = "Enter Phone Number In Correct Format (e.g., 1234 1234 12, 12345 12345, +91 98765 43210 or 9876543210)"
    )
    private String phone;

    private String gender;
}

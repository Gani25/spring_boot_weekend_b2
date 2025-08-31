package com.sprk.student_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rollNo;

    private String firstName;

    private String lastName;

    private int age;

    private String gender;

    private String email;

    private String phoneNumber;
}

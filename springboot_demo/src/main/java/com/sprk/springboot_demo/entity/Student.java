package com.sprk.springboot_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class Student {

    private int rollNo;

    private String firstName;

    private String lastName;

    private String gender;

    // Lombok Dependency-> Getter/Setter, Constructor, ToString
}

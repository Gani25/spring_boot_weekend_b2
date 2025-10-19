package com.gani.mvc_demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private int rollNo;

    private String name;

    private String gender;

    private boolean premium;
}

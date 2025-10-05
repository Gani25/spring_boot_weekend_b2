package com.sprk.one_to_many.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollno;

    private String name;

    private String gender;

    private String phone;

    private int age;

    // One To Many
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH},mappedBy = "student")
    @JsonManagedReference
    private List<Course> coursesEnrolled;



}

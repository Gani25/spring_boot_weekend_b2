package com.sprk.many_to_many.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString(exclude = {"coursesEnrolled"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollno;

    private String name;

    private String gender;

    private String phone;

    private int age;

    // One To Many
    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "rollno"),
            inverseJoinColumns = @JoinColumn(name = "course_id")

    )

    private List<Course> coursesEnrolled;



}

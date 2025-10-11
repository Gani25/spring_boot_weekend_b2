package com.sprk.many_to_many.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;

    private String courseDescription;

    private String courseDuration;

    // Many to Many
    @ManyToMany(mappedBy = "coursesEnrolled")
    @JsonIgnore
    private List<Student> students;
}

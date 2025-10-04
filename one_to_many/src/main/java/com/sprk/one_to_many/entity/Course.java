package com.sprk.one_to_many.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;

    private String courseDescription;

    private String courseDuration;

    // Many to One
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roll_no")
    @JsonBackReference
    private Student student;
}

//package com.sprk.one_to_many.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//public class StudentDetail {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int studentDetailId;
//
//    private String hobby;
//
//    private String education;
//
//    @OneToOne(mappedBy = "studentDetail")
//    @JsonBackReference
//    private Student student;
//
//
//}

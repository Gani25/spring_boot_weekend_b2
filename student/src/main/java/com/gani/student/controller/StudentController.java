package com.gani.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class StudentController {

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/student")
    public String showStudentForm() {
        return "student-form";
    }
}

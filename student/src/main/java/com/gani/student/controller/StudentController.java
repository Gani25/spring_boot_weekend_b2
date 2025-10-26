package com.gani.student.controller;

import com.gani.student.dto.StudentDto;
import com.gani.student.entity.Student;
import com.gani.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/student")
    public String showStudentForm(Model model) {
        model.addAttribute("studentDto", new StudentDto());
        return "student-form";
    }

    @PostMapping("/student")
    public String processStudentForm(@Valid @ModelAttribute("studentDto") StudentDto studentDto, BindingResult bindingResult) {
        System.out.println("I am inside Process");
        if(bindingResult.hasErrors()) {
            return "student-form";
        }
        if(studentService.findByEmail(studentDto.getEmail()).isPresent()){
            return "redirect:/student";
        }
        if(studentService.findByPhone(studentDto.getPhone()).isPresent()){
            return "redirect:/student";
        }
        System.out.println(studentDto);

        return "";
    }
}

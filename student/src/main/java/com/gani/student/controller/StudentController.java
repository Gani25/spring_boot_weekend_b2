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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String processStudentForm(@Valid @ModelAttribute("studentDto") StudentDto studentDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println("I am inside Process");
        if(bindingResult.hasErrors()) {
            return "student-form";
        }
        if(studentService.findByEmail(studentDto.getEmail()).isPresent()){
            redirectAttributes.addFlashAttribute("errorMsg", "Email Already Exists");
            return "redirect:/student";
        }
        if(studentService.findByPhone(studentDto.getPhone()).isPresent()){
            redirectAttributes.addFlashAttribute("errorMsg", "Phone Already Exists");
            return "redirect:/student";
        }

        Student savedStudent = studentService.save(studentDto);
        String successMsg = "Student with roll number: "+savedStudent.getRollNo()+", Saved Successfully";
        redirectAttributes.addFlashAttribute("successMsg", successMsg);
        return "redirect:/";
    }
}

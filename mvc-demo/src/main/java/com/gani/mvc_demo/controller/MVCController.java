package com.gani.mvc_demo.controller;

import com.gani.mvc_demo.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MVCController {

    @GetMapping("/demo")
    public String showDemoPage() {
        return "demo";
    }

    @GetMapping("/")
    public String showIndexPage(Model model) {

        model.addAttribute("companyName", "Infosys");
        model.addAttribute("empName", "Shobit Sharma");

        return "index";
    }

    @GetMapping("/show_info")
    public String showStudentInfo(Model model) {
        Student student = new Student(10, "Sprk Tech", "Female", true);

        model.addAttribute("student", student);

        return "info";

    }

    @GetMapping("/show_all")
    public String showStudents(Model model) {
        Student student1 = new Student(10, "Sprk Tech", "Female", true);
        Student student2 = new Student(50, "Sprk Tech", "Female", true);
        Student student3 = new Student(30, "Sprk Tech", "Female", false);
        Student student4 = new Student(1, "Sprk Tech", "Female", true);
        Student student5 = new Student(25, "Sprk Tech", "Female", false);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        model.addAttribute("students", students);

        return "students";

    }

}

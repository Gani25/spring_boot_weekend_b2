package com.gani.student.controller;

import com.gani.student.dto.StudentDto;
import com.gani.student.entity.Student;
import com.gani.student.service.StudentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller

public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/student")
    public String showStudentForm(Model model, HttpSession session) {
        model.addAttribute("studentDto", new StudentDto());
        session.removeAttribute("rollNo");
        return "student-form";
    }

    @PostMapping("/student")
    public String processStudentForm(@Valid @ModelAttribute("studentDto") StudentDto studentDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
        System.out.println("I am inside Process");
        if (bindingResult.hasErrors()) {
            return "student-form";
        }
        String rollNo = (String) session.getAttribute("rollNo"); // null
        System.out.println("Sesison RollNo = " + rollNo);
        Optional<Student> byEmail = studentService.findByEmail(studentDto.getEmail());
        Optional<Student> byPhone = studentService.findByPhone(studentDto.getPhone());

        // Insert
        if(rollNo == null || rollNo.isEmpty()) {
            if (byEmail.isPresent()) {

                model.addAttribute("errorMsg", "Email Already Exists");
                return "student-form";
            }

            if (byPhone.isPresent()) {
                model.addAttribute("errorMsg", "Phone Already Exists");
                return "student-form";
            }
        }else{
            // update
            if(byEmail.isPresent() && byEmail.get().getRollNo() != Integer.parseInt(rollNo)){
                model.addAttribute("errorMsg", "Email Already Taken By Another Student");
                return "student-form";
            }
            if(byPhone.isPresent() && byPhone.get().getRollNo() != Integer.parseInt(rollNo)){
                model.addAttribute("errorMsg", "Phone Number Already Taken By Another Student");
                return "student-form";
            }

        }

        // if rollNo is present then add rollNo for update
        if (rollNo != null) {
            studentDto.setRollNo(Integer.parseInt(rollNo));
        }
        Student savedStudent = studentService.save(studentDto);
        String successMsg;
        if (studentDto.getRollNo() != 0) {
            successMsg = "Student with roll number: " + savedStudent.getRollNo() + ", updated Successfully";

        } else {
            successMsg = "Student with roll number: " + savedStudent.getRollNo() + ", Saved Successfully";

        }
        redirectAttributes.addFlashAttribute("successMsg", successMsg);
        return "redirect:/";
    }

    @GetMapping("/student/list")
    public String showStudentList(Model model) {
        List<StudentDto> students = studentService.getListOfStudents();
        model.addAttribute("students", students);

        return "students";
    }

    @GetMapping("/student/delete/{rollNo}")
    public String deleteStudentByRollNo(@PathVariable String rollNo, RedirectAttributes redirectAttributes) {

        if (!Pattern.matches("\\d+", rollNo)) {
            // Strings
            redirectAttributes.addFlashAttribute("errorMsg", "Roll Number Not Valid");
            return "redirect:/student/list";

        }
        // Logic
        boolean result = studentService.deleteByRollNo(rollNo);
        if (result) {
            redirectAttributes.addFlashAttribute("successMsg", "Deleted Successfully");

        } else {
            redirectAttributes.addFlashAttribute("errorMsg", String.format("Roll Number = %s Not Found", rollNo));
        }

        return "redirect:/student/list";
    }

    @GetMapping("/student/show/{rollNo}")
    public String showUpdateFormByRollNo(@PathVariable String rollNo, RedirectAttributes redirectAttributes, Model model, HttpSession session) {

        if (!Pattern.matches("\\d+", rollNo)) {
            // Strings
            redirectAttributes.addFlashAttribute("errorMsg", "Roll Number Not Valid");
            return "redirect:/student/list";

        }
        // Logic
        StudentDto dbStudentDto = studentService.findByRollNo(rollNo);
        if (dbStudentDto == null) {
            redirectAttributes.addFlashAttribute("errorMsg", String.format("Roll Number = %s Not Found", rollNo));
            return "redirect:/student/list";
        }


        model.addAttribute("studentDto", dbStudentDto);
        session.setAttribute("rollNo",  String.valueOf(dbStudentDto.getRollNo()));
        return "student-form";
    }
}

package com.sprk.one_to_many.controller;

import com.sprk.one_to_many.entity.Course;
import com.sprk.one_to_many.entity.Student;
import com.sprk.one_to_many.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentRepository studentRepository;

    // Student -> Register id -> course purchase

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        // PostMan -> List Courses can be shared (Logic course will be purchased after creating account)
        student.setCoursesEnrolled(null);
        student.setRollno(0);
        return studentRepository.save(student);
    }


    @PutMapping("/purchase/{rollno}")
    public Student purchaseCourse(@PathVariable int rollno, @RequestBody List<Course> courses) {

        Student student = studentRepository.findById(rollno).orElseThrow(() -> new RuntimeException("Not Found"));

        List<Course> coursesEnrolled = student.getCoursesEnrolled();
        if (coursesEnrolled == null) {
            coursesEnrolled = new ArrayList<>();
        }
         courses =  courses
                .stream()
                .map((tempCourse) -> {
                    tempCourse.setStudent(student);
                    return tempCourse;
                })
                .toList();

        coursesEnrolled.addAll(courses);

        student.setCoursesEnrolled(coursesEnrolled);
        return studentRepository.save(student);


    }

    @GetMapping("/{rollno}")
    public Student getStudentByRollNo(@PathVariable int rollno){
        return studentRepository.findById(rollno).orElseThrow(() -> new RuntimeException("Not Found"));
    }

}

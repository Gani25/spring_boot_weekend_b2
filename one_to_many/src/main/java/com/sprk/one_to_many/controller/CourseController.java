package com.sprk.one_to_many.controller;

import com.sprk.one_to_many.entity.Course;
import com.sprk.one_to_many.entity.Student;
import com.sprk.one_to_many.repository.CourseRepository;
import com.sprk.one_to_many.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

//    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @DeleteMapping()
    public String deleteCourse(@RequestParam int courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("Course Not Found"));
        course.setStudent(null); // Break the reference

        courseRepository.delete(course);
        return "Course Deleted";
    }

}

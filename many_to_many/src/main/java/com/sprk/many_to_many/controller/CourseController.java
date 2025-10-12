package com.sprk.many_to_many.controller;

import com.sprk.many_to_many.entity.Course;
import com.sprk.many_to_many.entity.Student;
import com.sprk.many_to_many.repository.CourseRepository;
import com.sprk.many_to_many.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {


    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/add")
    public Course saveCourse(@RequestBody Course course) {
        course.setStudents(null);

        Course dbCourse = courseRepository.save(course);

        return dbCourse;
    }

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}

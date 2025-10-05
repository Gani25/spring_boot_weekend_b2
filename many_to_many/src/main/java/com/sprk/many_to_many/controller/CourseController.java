//package com.sprk.many_to_many.controller;
//
//import com.sprk.many_to_many.entity.Course;
//import com.sprk.many_to_many.repository.CourseRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/course")
//public class CourseController {
//
////    private final StudentRepository studentRepository;
//    private final CourseRepository courseRepository;
//
//    @DeleteMapping()
//    public String deleteCourse(@RequestParam int courseId){
//        Course course = courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("Course Not Found"));
//        course.setStudent(null); // Break the reference
//
//        courseRepository.delete(course);
//        return "Course Deleted";
//    }
//
//}

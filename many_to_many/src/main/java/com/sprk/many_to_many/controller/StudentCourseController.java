package com.sprk.many_to_many.controller;

import com.sprk.many_to_many.entity.Course;
import com.sprk.many_to_many.entity.Student;
import com.sprk.many_to_many.repository.CourseRepository;
import com.sprk.many_to_many.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/detail")
public class StudentCourseController {

    //    Buy -> Course(cid) / Login (rollNo)
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/purchase")
    public Student coursesEnrolled(@RequestParam int rollNo, @RequestParam int courseId) {

        Student dbStudent = studentRepository.findById(rollNo).orElseThrow(() -> new RuntimeException("Student Not Found"));

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course Not Found"));

        List<Course> courses = dbStudent.getCoursesEnrolled() == null ? new ArrayList<>() : dbStudent.getCoursesEnrolled();

        if (!(courses.contains(course))) {
            courses.add(course);
        }

        dbStudent.setCoursesEnrolled(courses);

        return studentRepository.save(dbStudent);
    }

    @PostMapping("/purchase/courses")
    public Student purchaseMultipleCourses(@RequestParam int rollNo, @RequestParam String courseId) {
        List<Integer> coursesId = new ArrayList<>();
        String courseIdStrArr[] = courseId.split(",");
        for (String courseIdStr : courseIdStrArr) {
            // System.out.println(courseIdStr);
            coursesId.add(Integer.parseInt(courseIdStr));
        }
//        System.out.println(courseId);
        Student dbStudent = studentRepository.findById(rollNo).orElseThrow(() -> new RuntimeException("Student Not Found"));
        List<Course> alreadyEnrolledCourses = dbStudent.getCoursesEnrolled() == null ? new ArrayList<>() : dbStudent.getCoursesEnrolled();

        List<Course> newCourses = new ArrayList<>();
        for(int theCourseId:coursesId){
            Course course = courseRepository.findById(theCourseId).orElse(null);
            if(course != null){
                if(!alreadyEnrolledCourses.contains(course)){
                    newCourses.add(course);
                }
            }
        }
        if(!newCourses.isEmpty()){
            alreadyEnrolledCourses.addAll(newCourses);
            dbStudent.setCoursesEnrolled(alreadyEnrolledCourses);
        }else{
            throw new RuntimeException("Already Courses Purchased or you haven't enrolled any courses");
        }
        Student student = studentRepository.save(dbStudent);
        return student;
    }

    // Student first enrolled but latter want to de-enrolled
    @DeleteMapping("/drop")
    public Student dropCourseFromStudent(@RequestParam int rollNo, @RequestParam int courseId) {

        Student dbStudent = studentRepository.findById(rollNo).orElseThrow(() -> new RuntimeException("Student Not Found"));

        Course enrolledCourse = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course Not Found"));

        List<Course> studentCoursesList = dbStudent.getCoursesEnrolled();
        if(!studentCoursesList.contains(enrolledCourse)){
            throw new RuntimeException(String.format("Course with id = %d, Not Found",courseId));
        }
        studentCoursesList.remove(enrolledCourse);
        dbStudent.setCoursesEnrolled(studentCoursesList);

        return studentRepository.save(dbStudent);


    }

}

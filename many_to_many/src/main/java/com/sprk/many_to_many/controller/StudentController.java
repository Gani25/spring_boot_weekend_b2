//package com.sprk.many_to_many.controller;
//
//import com.sprk.many_to_many.entity.Course;
//import com.sprk.many_to_many.entity.Student;
//import com.sprk.many_to_many.repository.CourseRepository;
//import com.sprk.many_to_many.repository.StudentRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/student")
//public class StudentController {
//
//    private final StudentRepository studentRepository;
//    private final CourseRepository courseRepository;
//
//    // Student -> Register id -> course purchase
//
//    @PostMapping("/add")
//    public Student addStudent(@RequestBody Student student) {
//        // PostMan -> List Courses can be shared (Logic course will be purchased after creating account)
//        student.setCoursesEnrolled(null);
//        student.setRollno(0);
//        return studentRepository.save(student);
//    }
//
//
//    @PutMapping("/purchase/{rollno}")
//    public Student purchaseCourse(@PathVariable int rollno, @RequestBody List<Course> courses) {
//
//        Student student = studentRepository.findById(rollno).orElseThrow(() -> new RuntimeException("Not Found"));
//
//        List<Course> coursesEnrolled = student.getCoursesEnrolled();
//        if (coursesEnrolled == null) {
//            coursesEnrolled = new ArrayList<>();
//        }
//        courses = courses
//                .stream()
//                .map((tempCourse) -> {
//                    tempCourse.setStudent(student);
//                    return tempCourse;
//                })
//                .toList();
//
//        coursesEnrolled.addAll(courses);
//
//        student.setCoursesEnrolled(coursesEnrolled);
//        return studentRepository.save(student);
//
//
//    }
//
//    @GetMapping("/{rollno}")
//    public Student getStudentByRollNo(@PathVariable int rollno) {
//        return studentRepository.findById(rollno).orElseThrow(() -> new RuntimeException("Not Found"));
//    }
//
//    @PutMapping("/update-student/{rollno}")
//    public Student updateStudent(@PathVariable int rollno, @RequestBody Student updatedStudent) {
//        Student student = studentRepository.findById(rollno).orElseThrow(() -> new RuntimeException("Not Found"));
//
//        student.setName(updatedStudent.getName());
//        student.setGender(updatedStudent.getGender());
//        student.setPhone(updatedStudent.getPhone());
//        student.setAge(updatedStudent.getAge());
//        return studentRepository.save(student);
//
//    }
//
//    @GetMapping("/all")
//    public List<Student> getAllStudents() {
//        return studentRepository.findAll();
//    }
//
//    // update course
//    @PutMapping("/update-course/{rollno}/{courseId}")
//    public Student updateCourse(@PathVariable int rollno, @PathVariable int courseId, @RequestBody Course updatedCourse) {
//        Student student = studentRepository.findById(rollno).orElseThrow(() -> new RuntimeException("Not Found"));
//
//        Course course = courseRepository.findByCourseIdAndStudent(courseId, student).orElseThrow(() -> new RuntimeException("Not Found"));
//
//
//        List<Course> coursesEnrolled = student.getCoursesEnrolled();
//        if (coursesEnrolled == null) {
//            throw new RuntimeException("Buy Course First");
//        }
//        for (Course courseEnrolled : coursesEnrolled) {
//            if (courseEnrolled.getCourseId() == course.getCourseId()) {
//                coursesEnrolled.remove(courseEnrolled);
//
//                courseEnrolled.setCourseDescription(updatedCourse.getCourseDescription());
//                courseEnrolled.setCourseName(updatedCourse.getCourseName());
//                courseEnrolled.setStudent(student);
//                courseEnrolled.setCourseDuration(updatedCourse.getCourseDuration());
//                coursesEnrolled.add(courseEnrolled);
//            }
//        }
//        student.setCoursesEnrolled(coursesEnrolled);
//        return studentRepository.save(student);
//
//    }
//
//    @DeleteMapping("/delete")
//    public String deleteStudent(@RequestParam int rollno) {
//        Student dbStudent = studentRepository.findById(rollno).orElseThrow(() -> new RuntimeException("Not Found"));
//
//        dbStudent
//                .getCoursesEnrolled()
//                .stream()
//                .map((course) -> {
//                    course.setStudent(null);
//                    return course;
//                })
//                .toList();
////        List<Course> updatedCourse = dbCourses
////                .stream()
////                .map((course) -> {
////                    course.setStudent(null);
////                    return course;
////                }).toList();
//
//        dbStudent.setCoursesEnrolled(null);
//        studentRepository.delete(dbStudent);
//        return "Student Deleted";
//    }
//
//}

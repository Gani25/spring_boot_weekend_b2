package com.sprk.springboot_demo.controller;

import com.sprk.springboot_demo.entity.Student;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class DemoController {

    private List<Student> students ;

    @PostConstruct
    public void init() {
        System.out.println("Initializing Empty Array");
        students = new ArrayList<>();
        students.add(new Student(1,"Abdul Gani","Memon","Male"));
        students.add(new Student(2,"Shubham","Mishra","Male"));
        students.add(new Student(3,"Rutuja","More","Female"));

    }

    @PreDestroy
    public void destroy() {
        System.out.println("Clearing all resources");
    }



    // Methods
//    @RequestMapping(value = {"/hello","/home"}, method = RequestMethod.GET)
    @GetMapping({"/hello", "/home"})
    public String sayHello() {
        return "Hello World";
    }

    @RequestMapping("/fruits")
    public List<String> sayFruits() {
//        List<String> fruits = new ArrayList<>();
//        fruits.add("Apple");
//        fruits.add("Banana");
//        fruits.add("Orange");
//        fruits.add("Watermelon");

        return Arrays.asList("Apple", "Banana", "Orange", "Pineapple", "Watermelon");
    }

    @RequestMapping("/number")
    public int sayNumber() {
        return 50;
    }

    // Request Parameters
    // create a mapping which accept 2 numbers display product of that number
    @GetMapping("/api/req-param/add")
    public String sayAddition(@RequestParam(name = "n1", defaultValue = "0") int num1, @RequestParam(defaultValue = "0") int num2) {
        int result = num1 + num2;
        return String.format("The addition of %d, %d = %d", num1, num2, result);
    }

    // Path Variable
    @GetMapping("/api/path-variable/add/{n1}/{num2}")
    public String sayAdditionPathVariable(@PathVariable(name = "n1") int num1, @PathVariable int num2) {
        int result = num1 + num2;
        return String.format("The addition of %d, %d = %d with path variable", num1, num2, result);
    }

    // create a mapping which accepts a number display fibonacci series till that number
    // input = 8 ->
    // Fibonacci Series till 8 iterations are:
    // 0 1 2 3 5 8 13 21

    @GetMapping("/api/fibo/{num}")
    public StringBuilder sayFibonacci(@PathVariable int num) {
        int n1 = 0;
        int n2 = 1;
        StringBuilder result = new StringBuilder();
        result.append("<h1>Fibonacci series from 1 to " + num + " is </h1>");
        result.append("<b>");
        result.append(n1 + ", ");
        result.append(n2 + ", ");
        for (int i = 2; i <= num; i++) {
            int n3 = n1 + n2;
            result.append(i == num ? n3 : n3 + ", ");
            n1 = n2;
            n2 = n3;
        }

        result.append("</b>");
        return result;
    }

    // Create a list of fruits of length 10 and display fruit name based on position
    // Apple, Orange, Mango, Litchi, Watermellon, Banana, Guava,Papaya, Musk Mellon, Custard Apple
    // pos = 8 -> Papaya
    // pos = 15 -> Only 10 fruits are there select from 1 to 10
    // pos = 0/-5 -> ENter positive number greater than 0

    // Create mapping which returns student object
    @GetMapping("/api/student")
    public Student showStudent(){
        Student student = new Student(1,"Abdul Gani","Memon","Male");
        return student;
    }

    // Read Data of Student and display on console
    @PostMapping("/api/student")
    public Student getStudent(@RequestBody Student student){
        System.out.println(student);
        return student;
    }

    // create list of student and display as Get Mapping
    @GetMapping("/api/students")
    public List<Student> showStudents(){

        return students;
    }

    // Find student by roll no if rollno is incorrect display error message

    @GetMapping("/student-roll-no")
    public ResponseEntity<?> showStudentByRollNo(@RequestParam int rollNo){
        Student student = null;
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                student = s;
            }
        }
        if(student == null){
            String msg = "Student with roll no = "+rollNo+" not found";
//            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
            return ResponseEntity.status(400).body(msg);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}

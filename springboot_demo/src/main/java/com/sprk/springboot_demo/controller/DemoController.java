package com.sprk.springboot_demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class DemoController {

    // Methods
//    @RequestMapping(value = {"/hello","/home"}, method = RequestMethod.GET)
    @GetMapping({"/hello","/home"})
    public String sayHello(){
        return "Hello World";
    }
    @RequestMapping("/fruits")
    public List<String> sayFruits(){
//        List<String> fruits = new ArrayList<>();
//        fruits.add("Apple");
//        fruits.add("Banana");
//        fruits.add("Orange");
//        fruits.add("Watermelon");

        return Arrays.asList("Apple","Banana","Orange","Pineapple","Watermelon");
    }

    @RequestMapping("/number")
    public int sayNumber(){
        return 50;
    }

    // Request Parameters
    // create a mapping which accept 2 numbers display product of that number
    @GetMapping("/api/req-param/add")
    public String sayAddition(@RequestParam(name = "n1", defaultValue = "0") int num1,@RequestParam(defaultValue = "0") int num2){
        int result = num1 + num2;
        return String.format("The addition of %d, %d = %d",num1,num2,result);
    }

    // Path Variable
    @GetMapping("/api/path-variable/add/{n1}/{num2}")
    public String sayAdditionPathVariable(@PathVariable(name = "n1") int num1,@PathVariable int num2){
        int result = num1 + num2;
        return String.format("The addition of %d, %d = %d with path variable",num1,num2,result);
    }

    // create a mapping which accepts a number display fibonacci series till that number
    // input = 8 ->
    // Fibonacci Series till 8 iterations are:
    // 0 1 2 3 5 8 13 21
}

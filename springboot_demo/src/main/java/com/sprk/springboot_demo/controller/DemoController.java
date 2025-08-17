package com.sprk.springboot_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class DemoController {

    // Methods
    @RequestMapping("/hello")
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
}

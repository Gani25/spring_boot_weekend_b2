package com.sprk.security_demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/home")
    public  String homePage(){
        return "Welcome to HomePage";
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_TESTER')")
    public  String testPage(){
        return "Test Page of Tester";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public  String adminPage(){
        return "Welcome to Admin Page Of Security Demo";
    }
}

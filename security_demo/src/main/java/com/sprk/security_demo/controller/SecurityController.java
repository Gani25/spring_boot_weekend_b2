package com.sprk.security_demo.controller;

import com.sprk.security_demo.entity.UserInfo;
import com.sprk.security_demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecurityController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/users")
    public List<UserInfo> getUsers() {
        return userInfoService.getAllUsers();
    }

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

    @GetMapping("/auth/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public  String authUserPage(){
        return "Welcome to User Page Of Security Demo";
    }
}

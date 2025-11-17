package com.sprk.security_demo.controller;

import com.sprk.security_demo.dto.LoginDto;
import com.sprk.security_demo.entity.UserInfo;
import com.sprk.security_demo.service.JwtService;
import com.sprk.security_demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecurityController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/users")
    public List<UserInfo> getUsers() {
        return userInfoService.getAllUsers();
    }

    @GetMapping("/home")
    public String homePage() {
        return "Welcome to HomePage";
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_TESTER')")
    public String testPage() {
        return "Test Page of Tester";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminPage() {
        return "Welcome to Admin Page Of Security Demo";
    }

    @GetMapping("/auth/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String authUserPage() {
        return "Welcome to User Page Of Security Demo";
    }


    @PostMapping("/generate-token")
    public String getToken(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getUsername(),
                                loginDto.getPassword()
                        )
                );
        if(authentication.isAuthenticated()) {
        return jwtService.generateToken(loginDto);

        }else{
            throw new UsernameNotFoundException("Username or password is incorrect");
        }
    }

}

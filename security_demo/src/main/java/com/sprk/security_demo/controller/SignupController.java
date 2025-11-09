package com.sprk.security_demo.controller;

import com.sprk.security_demo.entity.Role;
import com.sprk.security_demo.entity.UserInfo;
import com.sprk.security_demo.repository.RoleRepository;
import com.sprk.security_demo.repository.UserInfoRepository;
import com.sprk.security_demo.service.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class SignupController {

    // account creation -> signup -> username, email, password,...

    private final UserInfoService userInfoService;

    @PostMapping("/signup")
    public UserInfo signup(@RequestBody UserInfo userInfo) {
        return userInfoService.saveUserInfo(userInfo);
    }

}

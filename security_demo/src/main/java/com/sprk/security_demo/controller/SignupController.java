package com.sprk.security_demo.controller;

import com.sprk.security_demo.entity.Role;
import com.sprk.security_demo.entity.UserInfo;
import com.sprk.security_demo.repository.RoleRepository;
import com.sprk.security_demo.repository.UserInfoRepository;
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

    private final UserInfoRepository userInfoRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public UserInfo signup(@RequestBody UserInfo userInfo) {

        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        Role role = roleRepository.findByRoleName("ROLE_USER").get();
        userInfo.setRoles(Set.of(role));

        return userInfoRepository.save(userInfo);
    }

}

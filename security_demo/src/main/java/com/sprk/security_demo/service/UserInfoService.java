package com.sprk.security_demo.service;

import com.sprk.security_demo.entity.Role;
import com.sprk.security_demo.entity.UserInfo;
import com.sprk.security_demo.repository.RoleRepository;
import com.sprk.security_demo.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfo saveUserInfo(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        Role role = roleRepository.findByRoleName("ROLE_USER").get();
//        System.out.println("Role = "+role.getRoleName());
        userInfo.getRoles().add(role);

        UserInfo savedUser = userInfoRepository.save(userInfo);
        return savedUser;
    }
}

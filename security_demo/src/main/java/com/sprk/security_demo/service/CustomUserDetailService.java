package com.sprk.security_demo.service;

import com.sprk.security_demo.config.CustomUserDetails;
import com.sprk.security_demo.entity.Role;
import com.sprk.security_demo.entity.UserInfo;
import com.sprk.security_demo.repository.UserInfoRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String credential) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoRepository
                .findByUsername(credential)
                .or(()-> userInfoRepository.findByEmail(credential))
                .orElseThrow(()->new UsernameNotFoundException(String.format("User = %s not found",credential)));

        Set<Role> roles = userInfo.getRoles();
        userInfo.setRoles(roles);

        UserDetails userDetails = new CustomUserDetails(userInfo);

        return userDetails;
    }
}

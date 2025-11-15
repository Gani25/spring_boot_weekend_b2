package com.sprk.security_demo.config;

import com.sprk.security_demo.entity.Role;
import com.sprk.security_demo.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    
    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserInfo userInfo) {

        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();
        this.authorities = createAuthorities(userInfo.getRoles());
    }

    private Collection<? extends GrantedAuthority> createAuthorities(Collection<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if(roles != null){
             authorities = roles
                    .stream()
                    .map((role)-> new SimpleGrantedAuthority(role.getRoleName()))
                    .collect(Collectors.toSet());

        }
        return authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}

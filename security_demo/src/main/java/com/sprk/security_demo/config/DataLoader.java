package com.sprk.security_demo.config;

import com.sprk.security_demo.entity.Role;
import com.sprk.security_demo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(roleRepository.findByRoleName("ROLE_USER").isEmpty()){
            // insert
            Role role = new Role();
            role.setRoleName("ROLE_USER");
            roleRepository.save(role);
        }
    }
}

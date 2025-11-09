package com.sprk.security_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    // In Memory Database Login
    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails normal = User
                .withUsername("abdul")
                .password("{noop}1234")
                .roles("USER")
                .build();
        UserDetails admin = User
                .withUsername("ankesh")
                .password("{noop}1234")
                .roles("USER","ADMIN")
                .build();
        UserDetails tester = User
                .withUsername("rohan")
                .password("{noop}1234")
                .roles("USER","TESTER")
                .build();

        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(normal,admin,tester);

        return userDetailsService;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests(req-> req
                .requestMatchers("/signup","/home")
                .permitAll()
                .anyRequest()
                .authenticated()
        );

        http.logout(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());

        return http.build();
    }


}

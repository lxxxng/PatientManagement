package com.example.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure HTTP security for the application
        http
            // Define authorization rules
            .authorizeHttpRequests(authorize -> 
                // Allow any request to be accessed without authentication
                authorize.anyRequest().permitAll()
            )
            
            // Disable CSRF protection for the application (updated method)
            .csrf(csrf -> csrf.disable()); // Using lambda expression to disable CSRF

        // Return the configured HTTP security as a SecurityFilterChain
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

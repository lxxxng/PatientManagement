package com.example.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must be a valid")
    private String email;
    
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    // getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.auth_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_service.model.User;

public interface UserRepository extends JpaRepository <User, UUID>{
    Optional<User> findByEmail(String email);
}

package com.sarvika.authenticationservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarvika.authenticationservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
package com.sarvika.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarvika.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
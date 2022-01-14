package com.example.reservationsystem.Persistence;

import com.example.reservationsystem.Business.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
}

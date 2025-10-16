package com.example.hsf302_group1.repository;

import com.example.hsf302_group1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByNameContainingIgnoreCase(String name);
    List<User> findByBalanceLessThan(double balance);
}

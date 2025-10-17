package com.example.hsf302_group1.repository;

import com.example.hsf302_group1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByNameContaining(String name);
    List<User> findByMembership (String membership);
    List <User> findByBalanceGreaterThan(double balance);
    List<User> findByStatus (boolean status);
}

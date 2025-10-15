package com.example.hsf302_group1.service;

import com.example.hsf302_group1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    User saveUser(User user);
    void deleteUser(int id);
    List<User> findUsersByNameContaining(String name);
    List<User> findUsersByMembership(String membership);
    List<User> findUsersByBalanceGreaterThan(double balance);
    List<User> findActiveUsers ();
    User updateUserBalance(int id, double newBalance);

}

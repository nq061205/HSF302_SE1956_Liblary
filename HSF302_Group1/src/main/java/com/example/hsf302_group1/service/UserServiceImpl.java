/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.hsf302_group1.service;

import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
* @author Do Quang Huy_HE191197
*/
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findUsersByNameContaining(String name) {
        return userRepository.findByNamaeContaining(name);
    }

    @Override
    public List<User> findUsersByMembership(String membership) {
        return userRepository.findByMembership(membership);
    }

    @Override
    public List<User> findUsersByBalanceGreaterThan(double balance) {
        return userRepository.findByBalanceGreaterThan(balance);
    }

    @Override
    public List<User> findActiveUsers() {
        return userRepository.findByStatus(true);
    }

    @Override
    public User updateUserBalance(int id, double newBalance) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBalance(newBalance);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}

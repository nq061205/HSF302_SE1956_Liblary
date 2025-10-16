package com.example.hsf302_group1.service;

import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public void membershipUserRegister(User user, String type, double money){
        user.setBalance(user.getBalance() - money);
        user.setMembership(type.toUpperCase());
        user.setMembershipEndDate(Date.valueOf(LocalDate.now().plusMonths(1)));
        userRepository.save(user);
    }
}
package com.example.hsf302_group1.service;

import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.repository.UserRepository;

public interface UserService {
    User findByName(String name);
    User save(User user);

}

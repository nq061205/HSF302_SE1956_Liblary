package com.example.hsf302_group1.service.Implement;

import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.repository.UserRepository;
import com.example.hsf302_group1.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}

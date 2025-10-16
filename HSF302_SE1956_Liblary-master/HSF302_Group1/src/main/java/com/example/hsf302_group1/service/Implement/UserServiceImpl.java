package com.example.hsf302_group1.service.Implement;

import com.example.hsf302_group1.dto.UserDTO;
import com.example.hsf302_group1.mapper.UserMapper;
import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.repository.UserRepository;
import com.example.hsf302_group1.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserMapper::toSearchDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> searchUsersByName(String keyword) {
        return userRepo.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(UserMapper::toSearchDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserDTO> rechargeBalance(int userId, double amount) {
        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            double currentBalance = user.getBalance();
            user.setBalance(currentBalance + amount);

            User updatedUser = userRepo.save(user);
            return List.of(UserMapper.toSearchDTO(updatedUser));
        }
        return Collections.emptyList();
    }
}
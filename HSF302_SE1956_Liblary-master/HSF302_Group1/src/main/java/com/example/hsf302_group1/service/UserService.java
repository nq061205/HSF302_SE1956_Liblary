package com.example.hsf302_group1.service;

import com.example.hsf302_group1.dto.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    List<UserDTO> searchUsersByName(String keyword);
    List<UserDTO> rechargeBalance(int userId, double amount);
}
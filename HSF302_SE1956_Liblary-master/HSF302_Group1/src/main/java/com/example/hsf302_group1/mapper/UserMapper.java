package com.example.hsf302_group1.mapper;

import com.example.hsf302_group1.dto.UserDTO;
import com.example.hsf302_group1.model.User;

public class UserMapper {
    public static UserDTO toSearchDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setGender(user.isGender());
        dto.setDob(user.getDob());
        dto.setBalance(user.getBalance());
        dto.setMembership(user.getMembership());
        dto.setActive(user.isStatus());

        return dto;
    }
}
package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        Optional<User> userData = userService.getUserById(id);

        if (userData.isPresent()) {
            User user = userData.get();
            user.setName(userDetails.getName());
            user.setDob(userDetails.getDob());
            user.setBalance(userDetails.getBalance());
            user.setGender(userDetails.isGender());
            user.setMembership(userDetails.getMembership());
            user.setStatus(userDetails.isStatus());

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/search/name")
    public ResponseEntity<List<User>> getUserByName(@RequestParam String name) {
        List<User> users = userService.findUsersByNameContaining(name);

        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PatchMapping("/{id}/balance")
    public ResponseEntity<User> updateUserBalance(@PathVariable int id, @RequestParam double balance) {
        try {
            User updatedUser = userService.updateUserBalance(id, balance);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
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

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";

    }

    @GetMapping("/new")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("isNew", true);
        return "users/form";

    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("message", "Success");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/users";
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/users";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("isNew", false);
            return "users/form";
        }else {
            redirectAttributes.addFlashAttribute("message", "User not found!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/users";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("message", "Success");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/users";
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/users";
        }
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam(required = false) String name, Model model) {
        if(name != null && !name.isEmpty()) {
            model.addAttribute("users", userService.findUsersByNameContaining(name));
            model.addAttribute("searchKeyword", name);
        }else {
            model.addAttribute("users", userService.getAllUsers());
        }
        return "users/list";
    }

}

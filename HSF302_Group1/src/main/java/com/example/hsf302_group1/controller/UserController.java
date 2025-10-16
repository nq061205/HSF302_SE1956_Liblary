package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.service.AuthorService;
import com.example.hsf302_group1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable(name = "id") int id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/membership/{id}")
    public String membership(@PathVariable int id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "membership";
    }

    @PostMapping("/membership/{id}")
    public String membershipRegister(
            @PathVariable(name = "id") int id,
            @RequestParam(name = "price") double price,
            @RequestParam(name = "type") String type,
            Model model
    ) {
        User user = userService.findUserById(id);
        if(user.getBalance() >= price) {
            userService.membershipUserRegister(user, type, price);
            model.addAttribute("ok",true);
        }   else{
            model.addAttribute("ok",false);
        }
        model.addAttribute("user", userService.findUserById(id));
        return "membership";
    }
}

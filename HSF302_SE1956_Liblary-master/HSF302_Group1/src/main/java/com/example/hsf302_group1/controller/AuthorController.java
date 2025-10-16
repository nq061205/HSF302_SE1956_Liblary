package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.dto.AuthorSearchDTO;
import com.example.hsf302_group1.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String getAuthors(@RequestParam(required = false) String keyword, Model model) {
        List<AuthorSearchDTO> authors;

        if (keyword != null && !keyword.isEmpty()) {
            authors = authorService.searchAuthorsByName(keyword);
        } else {
            authors = authorService.getAllAuthors();
        }

        model.addAttribute("authors", authors);
        model.addAttribute("keyword", keyword);
        return "AuthorSearch";
    }
}

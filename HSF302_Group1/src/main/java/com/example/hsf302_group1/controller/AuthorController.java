package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.model.Author;
import com.example.hsf302_group1.service.AuthorService;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors/list"; // Thymeleaf template name
    }

    @GetMapping("/new")
    public String showNewAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("isNew", true);
        return "authors/form";
    }

    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute Author author, RedirectAttributes redirectAttributes) {
        try {
            authorService.saveAuthor(author);
            redirectAttributes.addFlashAttribute("message", "Author saved successfully!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/authors";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/authors";
        }
    }


    @GetMapping("/edit/{id}")
    public String showEditAuthorForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Author> author = authorService.getAuthorById(id);
        if(author.isPresent()) {
            model.addAttribute("author", author.get());
            model.addAttribute("isNew", false);
            return "authors/form";
        } else {
            redirectAttributes.addFlashAttribute("message", "Author not found!");
            redirectAttributes.addFlashAttribute("alertClass", "alert_danger");
            return "redirect:/authors";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            authorService.deleteAuthor(id);
            redirectAttributes.addFlashAttribute("messsage", "Author deleted successfully!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/authors";

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/authors";
        }
    }


}

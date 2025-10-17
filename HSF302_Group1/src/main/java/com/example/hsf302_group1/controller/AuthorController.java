package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.model.Author;
import com.example.hsf302_group1.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // Display list of authors
    @GetMapping
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "author/list";
    }

    // Show form to create a new author
    @GetMapping("/new")
    public String showNewAuthorForm(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        model.addAttribute("pageTitle", "Add New Author");
        return "author/form";
    }

    // Save new author
    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute Author author, RedirectAttributes redirectAttributes) {
        try {
            authorService.saveAuthor(author);
            redirectAttributes.addFlashAttribute("message", "Author saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/authors";
    }

    // Show form to edit an author
    @GetMapping("/edit/{id}")
    public String showEditAuthorForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Author author = authorService.getAuthorById(id)
                    .orElseThrow(() -> new Exception("Author not found with ID: " + id));

            model.addAttribute("author", author);
            model.addAttribute("pageTitle", "Edit Author (ID: " + id + ")");

            return "author/form";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/authors";
        }
    }

    // Delete an author
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            authorService.deleteAuthorById(id);
            redirectAttributes.addFlashAttribute("message", "Author deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/authors";
    }
}
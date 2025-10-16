package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model)  {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list";
    }

    @GetMapping("/new")
    public String showNewBookForm(Model model) {
        model.addAttribute("book" , new Book());
        model.addAttribute("isNew", true);
        return "books/form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        try {
            bookService.saveBook(book);
            redirectAttributes.addFlashAttribute("message" , "Book saved successfully!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/books";
        }catch (Exception e){
            redirectAttributes.addAttribute("message" , "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/books";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Book> book = bookService.getBookById(id);
        if(book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("isNew", false);
            return "books/form";
        }else {
            redirectAttributes.addAttribute("message", "Book not found!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/books";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute("message", "Book deleted successfully!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }

        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(required = false) String name,
                              Model model) {
        if(name != null && !name.isEmpty()) {
            model.addAttribute("books", bookService.findBooksByNameContaining(name));
            model.addAttribute("searchKeyword", name);
        }else {
            model.addAttribute("books", bookService.getAllBooks());
        }

        return "books/list";
    }
}

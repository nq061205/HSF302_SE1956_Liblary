package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.model.Author;
import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.service.AuthorService;
import com.example.hsf302_group1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    // Display list of books
    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    // Show form to create a new book
    @GetMapping("/new")
    public String showNewBookForm(Model model) {
        Book book = new Book();
        List<Author> allAuthors = authorService.getAllAuthors();

        model.addAttribute("book", book);
        model.addAttribute("allAuthors", allAuthors);
        model.addAttribute("pageTitle", "Thêm sách mới");

        return "book/form";
    }

    // Save book or create new author and then assign to book
    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book,
                           @RequestParam(value = "authorIds", required = false) List<Integer> authorIds,
                           @RequestParam(value = "action", defaultValue = "saveBook") String action,
                           @RequestParam(value = "newAuthorName", required = false) String newAuthorName,
                           @RequestParam(value = "newAuthorDob", required = false) String newAuthorDob,
                           @RequestParam(value = "newAuthorQuotes", required = false) String newAuthorQuotes,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        try {
            // Nếu action là createAuthor thì tạo mới tác giả trước
            if ("createAuthor".equals(action) && newAuthorName != null && !newAuthorName.trim().isEmpty()) {
                // Tạo tác giả mới
                Author newAuthor = new Author();
                newAuthor.setName(newAuthorName.trim());
                newAuthor.setDob(newAuthorDob);
                newAuthor.setQuotes(newAuthorQuotes);

                Author savedAuthor = authorService.saveAuthor(newAuthor);


                model.addAttribute("message", "Tác giả \"" + savedAuthor.getName() + "\" đã được thêm thành công!");

                //them tac gia vao sach hien co
                if (book.getId() > 0) {
                    bookService.addAuthorToBook(book.getId(), savedAuthor.getId());
                }

                //lay danh sach tac gia moi nhta
                List<Author> allAuthors = authorService.getAllAuthors();

                //lay lai sach moi nhat
                if (book.getId() > 0) {
                    book = bookService.getBookById(book.getId()).orElse(book);
                }


                model.addAttribute("book", book);
                model.addAttribute("allAuthors", allAuthors);
                model.addAttribute("pageTitle", book.getId() > 0 ? "Sửa sách (ID: " + book.getId() + ")" : "Thêm sách mới");

                return "book/form";
            }
            // new action la saveBook thi luu sach thoi
            else {
                if (book.getId() > 0) {
                    // cap nhat sach vs tac gia moi
                    bookService.updateBookWithAuthors(book, authorIds);
                    redirectAttributes.addFlashAttribute("message", "Sách đã được cập nhật thành công!");
                } else {
                    // luu sach moi
                    Book savedBook = bookService.saveBook(book);

                    // them tac gia vao sach neu co
                    if (authorIds != null && !authorIds.isEmpty()) {
                        for (Integer authorId : authorIds) {
                            bookService.addAuthorToBook(savedBook.getId(), authorId);
                        }
                    }

                    redirectAttributes.addFlashAttribute("message", "Sách đã được thêm thành công!");
                }

                return "redirect:/books";
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
            return "redirect:/books";
        }
    }

    // Show form to edit a book
    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Book book = bookService.getBookById(id)
                    .orElseThrow(() -> new Exception("Không tìm thấy sách với ID: " + id));

            List<Author> allAuthors = authorService.getAllAuthors();

            model.addAttribute("book", book);
            model.addAttribute("allAuthors", allAuthors);
            model.addAttribute("pageTitle", "Sửa sách (ID: " + id + ")");

            return "book/form";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/books";
        }
    }

    // Delete a book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteBookById(id);
            redirectAttributes.addFlashAttribute("message", "Sách đã được xóa thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }
        return "redirect:/books";
    }

    // Remove author from book
    @GetMapping("/{bookId}/remove-author/{authorId}")
    public String removeAuthorFromBook(@PathVariable("bookId") int bookId,
                                       @PathVariable("authorId") int authorId,
                                       RedirectAttributes redirectAttributes) {
        try {
            bookService.removeAuthorFromBook(bookId, authorId);
            redirectAttributes.addFlashAttribute("message", "Đã xóa tác giả khỏi sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi: " + e.getMessage());
        }
        return "redirect:/books/edit/" + bookId;
    }
}
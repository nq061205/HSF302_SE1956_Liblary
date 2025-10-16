package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.dto.BorrowRequestDTO;
import com.example.hsf302_group1.dto.BorrowResponseDTO;
import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.service.BookService;
import com.example.hsf302_group1.service.BorrowBookService;
import com.example.hsf302_group1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BorrowBookController {

    private final BorrowBookService borrowBookService;
    private final BookService bookService;
    private final UserService userService;

    public BorrowBookController(BorrowBookService borrowBookService, BookService bookService, UserService userService) {
        this.borrowBookService = borrowBookService;
        this.bookService = bookService;
        this.userService = userService;
    }

    // Hiển thị form mượn sách
    @GetMapping("/borrow")
    public String showBorrowForm(Model model) {
        BorrowRequestDTO borrowRequestDTO = new BorrowRequestDTO();

        List<Book> books = bookService.getAllBooks();


        borrowRequestDTO.setBook(new Book());
        borrowRequestDTO.setUser(new User());

        model.addAttribute("books", books);

        model.addAttribute("borrowRequestDTO", borrowRequestDTO);

        return "BorrowBook";
    }

    @PostMapping("/borrow")
    public String borrowBook(@ModelAttribute BorrowRequestDTO request, Model model) {

        // Lấy book
        if (request.getBook() == null) {
            model.addAttribute("message", "Vui lòng chọn sách!");
            model.addAttribute("books", bookService.getAllBooks());
            model.addAttribute("borrowRequestDTO", new BorrowRequestDTO());
            return "BorrowBook";
        }
        Book book = bookService.getBookById(request.getBook().getId());
        if (book == null) {
            model.addAttribute("message", "Sách không tồn tại!");
            model.addAttribute("books", bookService.getAllBooks());
            model.addAttribute("borrowRequestDTO", new BorrowRequestDTO());
            return "BorrowBook";
        }

        // Kiểm tra trạng thái sách
        if (borrowBookService.existsByBookAndStatus(book, "borrowed")) {
            model.addAttribute("message", "Sách này đang được mượn, không thể mượn thêm!");
            model.addAttribute("books", bookService.getAllBooks());
            model.addAttribute("borrowRequestDTO", new BorrowRequestDTO());
            return "BorrowBook";
        }

        // Xử lý user
        User user = request.getUser();
        User existingUser = userService.findByName(user.getName());
        if (existingUser == null) {
            userService.save(user);
        } else {
            user = existingUser;
        }

        request.setBook(book);
        request.setUser(user);

        // Mượn sách
        BorrowResponseDTO response = borrowBookService.borrowBook(request);

        model.addAttribute("message", "Mượn sách thành công!");
        model.addAttribute("borrowInfo", response);
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("borrowRequestDTO", new BorrowRequestDTO());

        return "BorrowBook";
    }


    @GetMapping("/return")
    public String showReturnForm(Model model) {

        List<BorrowResponseDTO> borrowedBooks = borrowBookService.getAllBorrowed();

        model.addAttribute("borrowedBooks", borrowedBooks);
        return "returnBook";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam("id") int borrowId, Model model) {
        BorrowResponseDTO response = borrowBookService.returnBook(borrowId);

        model.addAttribute("borrow", response);
        model.addAttribute("message", "Trả sách thành công!");


        List<BorrowResponseDTO> borrowedBooks = borrowBookService.getAllBorrowed();
        model.addAttribute("borrowedBooks", borrowedBooks);

        return "returnBook";
    }
}

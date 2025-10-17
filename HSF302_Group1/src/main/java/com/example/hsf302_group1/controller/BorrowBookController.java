package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.dto.BorrowRequestDTO;
import com.example.hsf302_group1.dto.BorrowResponseDTO;
import com.example.hsf302_group1.mapper.BorrowBookMapper;
import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.model.BorrowBook;
import com.example.hsf302_group1.model.User;
import com.example.hsf302_group1.service.BookService;
import com.example.hsf302_group1.service.BorrowBookService;
import com.example.hsf302_group1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
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

        if (borrowBookService.existsByBookAndStatus(book, "borrowed")) {
            model.addAttribute("message", "Sách này đang được mượn, không thể mượn thêm!");
            model.addAttribute("books", bookService.getAllBooks());
            model.addAttribute("borrowRequestDTO", new BorrowRequestDTO());
            return "BorrowBook";
        }

        User user = request.getUser();
        User existingUser = userService.findByName(user.getName());
        if (existingUser == null) {
            userService.save(user);
        } else {
            user = existingUser;
        }


        LocalDate memberEndDate = user.getMembershipEndDate().toLocalDate();

        LocalDate memberStartDate = memberEndDate.minusMonths(1);

        int count = borrowBookService.countByUserIdAndStatusAndDateBorrowBetween(user.getId(), "borrowed", Date.valueOf(memberStartDate), Date.valueOf(memberEndDate));

        if ("ordinary".equalsIgnoreCase(user.getMembership())) {
            if (count >= 5) {
                model.addAttribute("message", "Bạn đã dùng hết lượt được mượn sác trong tháng này!Vui lòng mua gói vip hơn");
                model.addAttribute("books", bookService.getAllBooks());
                model.addAttribute("borrowRequestDTO", new BorrowRequestDTO());
                return "BorrowBook";
            }
        } else if ("advance".equalsIgnoreCase(user.getMembership())) {
            if (count >= 10) {
                model.addAttribute("message", "Bạn đã dùng hết lượt được mượn sác trong tháng này!Vui lòng mua gói vip hơn");
                model.addAttribute("books", bookService.getAllBooks());
                model.addAttribute("borrowRequestDTO", new BorrowRequestDTO());
                return "BorrowBook";
            }
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

        BorrowBook borrow = borrowBookService.findById(borrowId);
        LocalDate now = LocalDate.now();
        LocalDate dueDate = borrow.getDateReturned().toLocalDate();

        User user = userService.findById(borrow.getUser().getId());

        int fine = 0;
        if (now.isAfter(dueDate)) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(dueDate, now);
            fine = (int) (daysLate * 30000);
        }

        if (user.getBalance() < fine) {
            user.setStatus(false);
            user.setBalance(0);
            userService.save(user);
            model.addAttribute("message", "Bạn không đủ tiền để trả phạt! Tài khoản bị khóa.");
            return "returnBook";
        }

        user.setBalance(user.getBalance() - fine);
        userService.save(user);

        borrow.setDateReturned(Date.valueOf(now));
        borrow.setStatus("RETURNED");
        borrowBookService.saveEntity(borrow);

        BorrowResponseDTO responseDTO = BorrowBookMapper.toBorrowResponseDTO(borrow);

        model.addAttribute("borrow", responseDTO);
        model.addAttribute("message", "Trả sách thành công! Tiền phạt: " + fine + " VNĐ");

        List<BorrowResponseDTO> borrowedBooks = borrowBookService.getAllBorrowed();
        model.addAttribute("borrowedBooks", borrowedBooks);

        return "returnBook";
    }

}

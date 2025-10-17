package com.example.hsf302_group1.service.Implement;

import com.example.hsf302_group1.dto.AuthorSearchDTO;
import com.example.hsf302_group1.dto.BorrowRequestDTO;
import com.example.hsf302_group1.dto.BorrowResponseDTO;
import com.example.hsf302_group1.mapper.AuthorMapper;
import com.example.hsf302_group1.mapper.BorrowBookMapper;
import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.model.BorrowBook;
import com.example.hsf302_group1.repository.BookRepository;
import com.example.hsf302_group1.repository.BorrowBookRepository;
import com.example.hsf302_group1.service.BorrowBookService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class BorrowBookServiceImpl implements BorrowBookService {
    private final BorrowBookRepository borrowBookRepo;
    private final BookRepository bookRepo;

    public BorrowBookServiceImpl(BorrowBookRepository borrowBookRepo, BookRepository bookRepo) {
        this.borrowBookRepo = borrowBookRepo;
        this.bookRepo = bookRepo;
    }


    @Override
    public BorrowResponseDTO borrowBook(BorrowRequestDTO request) {
        BorrowResponseDTO response = new BorrowResponseDTO();
        Book book = bookRepo.findById(request.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sách"));
        boolean isBorrowed = borrowBookRepo.existsByBookAndStatus(book, "borrowed");
        if (isBorrowed) {
            response.setStatus("borrowed");
            return response;
        }
        BorrowBook borrow = new BorrowBook();
        borrow.setUser(request.getUser());
        borrow.setBook(request.getBook());
        LocalDate localdate = LocalDate.now();
        Date date = Date.valueOf(localdate);
        LocalDate returndate = localdate.plusDays(30);
        Date dateReturn = Date.valueOf(returndate);
        borrow.setDateBorrow(date);
        borrow.setStatus("BORROWED");
        borrow.setDateReturned(dateReturn);

        BorrowBook saved = borrowBookRepo.save(borrow);
        return BorrowBookMapper.toBorrowResponseDTO(saved);
    }

    @Override
    public BorrowResponseDTO returnBook(int borrowId) {
        BorrowBook borrow = borrowBookRepo.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        borrow.setDateReturned(Date.valueOf(LocalDate.now()));
        borrow.setStatus("RETURNED");

        BorrowBook updated = borrowBookRepo.save(borrow);
        return BorrowBookMapper.toBorrowResponseDTO(updated);
    }

    @Override
    public boolean existsByBookAndStatus(Book book, String status) {
        return borrowBookRepo.existsByBookAndStatus(book, status);
    }

    @Override
    public List<BorrowResponseDTO> getAllBorrowed() {
        // Lấy tất cả bản ghi Borrow có status = "borrowed"
        List<BorrowBook> borrowedList = borrowBookRepo.findByStatus("borrowed");

        // Chuyển sang DTO
        List<BorrowResponseDTO> borrowedDTOs = borrowedList.stream()
                .map(b -> new BorrowResponseDTO(
                        b.getId(),
                        b.getUser(),
                        b.getBook(),
                        b.getDateBorrow(),
                        b.getDateReturned(),
                        b.getStatus()
                ))
                .toList();

        return borrowedDTOs;
    }

    @Override
    public int countByUserIdAndStatusAndDateBorrowBetween(int userId,String status, Date startDate, Date endDate) {
        return borrowBookRepo.countByUserIdAndStatusAndDateBorrowBetween(userId,status, startDate, endDate);
    }

    @Override
    public BorrowResponseDTO save(BorrowResponseDTO borrowResponseDTO) {
        BorrowBook borrow = BorrowBookMapper.toBorrowBook(borrowResponseDTO);
        BorrowBook saved = borrowBookRepo.save(borrow);
        return BorrowBookMapper.toBorrowResponseDTO(saved);
    }

    @Override
    public BorrowBook findById(int borrowId) {
        // Trả về BorrowBook nếu tồn tại, nếu không ném exception
        return borrowBookRepo.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found with ID: " + borrowId));
    }

    @Override
    public BorrowBook saveEntity(BorrowBook borrowBook) {
        return borrowBookRepo.save(borrowBook);

    }


}

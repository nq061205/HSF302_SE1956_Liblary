package com.example.hsf302_group1.service;

import com.example.hsf302_group1.dto.BorrowRequestDTO;
import com.example.hsf302_group1.dto.BorrowResponseDTO;
import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.model.BorrowBook;
import com.example.hsf302_group1.model.User;

import java.sql.Date;
import java.util.List;

public interface BorrowBookService {

    BorrowResponseDTO borrowBook(BorrowRequestDTO request);

    BorrowResponseDTO returnBook(int borrowId);

    boolean existsByBookAndStatus(Book book, String status);

    List<BorrowResponseDTO> getAllBorrowed();

    int countByUserIdAndStatusAndDateBorrowBetween(int userId,String status, Date startDate, Date endDate);

    BorrowResponseDTO save(BorrowResponseDTO borrowResponseDTO);

    BorrowBook findById(int borrowId);

    BorrowBook saveEntity(BorrowBook borrowBook);
}

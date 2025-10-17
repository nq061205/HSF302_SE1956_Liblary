package com.example.hsf302_group1.mapper;

import com.example.hsf302_group1.dto.BorrowRequestDTO;
import com.example.hsf302_group1.dto.BorrowResponseDTO;
import com.example.hsf302_group1.model.BorrowBook;

public class BorrowBookMapper {

    public static BorrowRequestDTO toBorrowRequestDTO(BorrowBook book) {
        BorrowRequestDTO dto = new BorrowRequestDTO();
        dto.setBook(book.getBook());
        dto.setUser(book.getUser());
        return dto;
    }

    public static BorrowResponseDTO toBorrowResponseDTO(BorrowBook book) {
        BorrowResponseDTO  dto = new BorrowResponseDTO();
        dto.setId(book.getId());
        dto.setBook(book.getBook());
        dto.setUser(book.getUser());
        dto.setBorrowDate(book.getDateBorrow());
        dto.setReturnDate(book.getDateReturned());
        return dto;
    }

    public static BorrowBook toBorrowBook(BorrowResponseDTO dto) {
        BorrowBook borrow = new BorrowBook();
        borrow.setId(dto.getId());
        borrow.setBook(dto.getBook());
        borrow.setUser(dto.getUser());
        borrow.setDateBorrow(dto.getBorrowDate());
        borrow.setDateReturned(dto.getReturnDate());
        borrow.setStatus(dto.getStatus());
        return borrow;
    }
}

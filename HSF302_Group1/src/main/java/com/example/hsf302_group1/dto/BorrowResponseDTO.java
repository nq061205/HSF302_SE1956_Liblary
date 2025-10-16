package com.example.hsf302_group1.dto;

import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.model.User;

import java.sql.Date;

public class BorrowResponseDTO {
    private int id;
    private User user;
    private Book book;
    private Date borrowDate;
    private Date returnDate;
    private String status;

    public BorrowResponseDTO() {
    }

    public BorrowResponseDTO(int id, User user, Book book, Date borrowDate, Date returnDate, String status) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

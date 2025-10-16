package com.example.hsf302_group1.dto;

import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.model.User;

public class BorrowRequestDTO {
    private User user;
    private Book book;

    public BorrowRequestDTO() {
    }

    public BorrowRequestDTO(User user, Book book) {
        this.user = user;
        this.book = book;
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
}

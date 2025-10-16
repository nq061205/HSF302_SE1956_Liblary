package com.example.hsf302_group1.service;

import com.example.hsf302_group1.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Integer id);
}

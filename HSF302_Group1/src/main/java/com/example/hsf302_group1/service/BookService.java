package com.example.hsf302_group1.service;

import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.model.BorrowBook;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(int id);
    Book saveBook(Book book);
    void deleteBook(int id);
    List<Book> findBooksByNameContaining(String name);
    List<Book> findBooksByType(String type);
    List<Book> findBooksByPriceLessThan(double price);

 }

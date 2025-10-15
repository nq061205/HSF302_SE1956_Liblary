package com.example.hsf302_group1.service;

import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findBooksByNameContaining(String name) {
        return bookRepository.findByNameContaining(name);
    }

    @Override
    public List<Book> findBooksByType(String type) {
        return bookRepository.findByType(type);
    }

    @Override
    public List<Book> findBooksByPriceLessThan(double price) {
        return bookRepository.findByPriceLessThan(price);
    }
}

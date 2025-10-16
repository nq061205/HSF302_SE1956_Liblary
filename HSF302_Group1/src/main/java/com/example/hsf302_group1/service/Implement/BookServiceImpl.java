package com.example.hsf302_group1.service.Implement;

import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.repository.BookRepository;
import com.example.hsf302_group1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl  implements BookService {
    private final BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElse(null);
    }
}

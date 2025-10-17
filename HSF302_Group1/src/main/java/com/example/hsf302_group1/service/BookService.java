package com.example.hsf302_group1.service;

import com.example.hsf302_group1.model.Author;
import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.repository.AuthorRepository;
import com.example.hsf302_group1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }


    @Transactional
    public void deleteBookById(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sách với ID: " + id));

        // xoa quan he vs tac gia
        if (book.getAuthorList() != null) {

            for (Author author : book.getAuthorList()) {
                author.getBookList().remove(book);
            }

            book.getAuthorList().clear();
        }


        bookRepository.deleteById(id);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    // Method to add an author to a book
    public Book addAuthorToBook(int bookId, int authorId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        if (book.getAuthorList() == null) {
            book.setAuthorList(new ArrayList<>());
        }

        if (!book.getAuthorList().contains(author)) {
            book.getAuthorList().add(author);
            author.addBook(book);
        }

        return bookRepository.save(book);
    }

    // Method to remove an author from a book
    public Book removeAuthorFromBook(int bookId, int authorId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        if (book.getAuthorList() != null) {
            book.getAuthorList().remove(author);
            author.removeBook(book);
        }

        return bookRepository.save(book);
    }

    // Method to update book with a list of authors
    public Book updateBookWithAuthors(Book bookDetails, List<Integer> authorIds) {
        Book book = bookRepository.findById(bookDetails.getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Update basic book details
        book.setName(bookDetails.getName());
        book.setPrice(bookDetails.getPrice());
        book.setType(bookDetails.getType());

        // Clear existing authors and add new ones
        if (book.getAuthorList() != null) {
            book.getAuthorList().clear();
        } else {
            book.setAuthorList(new ArrayList<>());
        }

        if (authorIds != null && !authorIds.isEmpty()) {
            List<Author> authors = authorRepository.findAllById(authorIds);
            for (Author author : authors) {
                book.getAuthorList().add(author);
                author.addBook(book);
            }
        }

        return bookRepository.save(book);
    }
}
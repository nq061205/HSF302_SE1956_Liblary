package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book saveBook = bookService.saveBook(book);
        return new ResponseEntity<>(saveBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book bookDetails) {
        Optional<Book> bookData = bookService.getBookById(id);

        if(bookData.isPresent()) {
            Book book = bookData.get();
            book.setName(bookDetails.getName());
            book.setPrice(bookDetails.getPrice());
            book.setType(bookDetails.getType());

            return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable int id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<Book>> searchBookByName(@RequestParam String name) {
        List<Book> books = bookService.findBooksByNameContaining(name);

        if(books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/search/type")
    public ResponseEntity<List<Book>> searchBookByType(@RequestParam String type) {
        List<Book> books = bookService.findBooksByType(type);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}

package com.example.hsf302_group1.controller;

import com.example.hsf302_group1.model.Author;
import com.example.hsf302_group1.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
        Optional<Author> author = authorService.getAuthorById(id);
        return author.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    //Update an existing author
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author authorDetails) {
        Optional<Author> authorData = authorService.getAuthorById(id);

        if(authorData.isPresent()) {
            Author author = authorData.get();
            author.setName(authorDetails.getName());
            author.setDob(authorDetails.getDob());
            author.setQuotes(authorDetails.getQuotes());

            return new ResponseEntity<>(authorService.saveAuthor(author), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable int id) {
        try {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Author>> searchAuthorsByName(@RequestParam String name) {
        List<Author> authors = authorService.findAuthorsByNameContaining(name);
        if(authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}

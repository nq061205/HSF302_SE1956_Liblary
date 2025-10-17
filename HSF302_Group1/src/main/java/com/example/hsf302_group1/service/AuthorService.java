package com.example.hsf302_group1.service;

import com.example.hsf302_group1.model.Author;
import com.example.hsf302_group1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthorById(int id) {
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }
}
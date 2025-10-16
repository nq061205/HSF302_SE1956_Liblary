package com.example.hsf302_group1.service;

import com.example.hsf302_group1.model.Author;

import com.example.hsf302_group1.dto.AuthorSearchDTO;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorSearchDTO> getAllAuthors();
    List<AuthorSearchDTO> searchAuthorsByName(String keyword);
    List<Author> getAllAuthors();
    Optional<Author> getAuthorById(int id);
    Author saveAuthor(Author author);
    void deleteAuthor(int id);
    List<Author> findAuthorsByNameContaining(String name);
}

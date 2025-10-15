package com.example.hsf302_group1.service;

import com.example.hsf302_group1.model.Author;
import com.example.hsf302_group1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(int id) {
         authorRepository.deleteById(id);
    }

    @Override
    public List<Author> findAuthorsByNameContaining(String name) {
        return authorRepository.findByNameContaining(name);
    }
}

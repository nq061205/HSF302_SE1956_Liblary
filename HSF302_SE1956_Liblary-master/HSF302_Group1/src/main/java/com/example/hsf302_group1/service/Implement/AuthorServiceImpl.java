package com.example.hsf302_group1.service.Implement;

import com.example.hsf302_group1.dto.AuthorSearchDTO;
import com.example.hsf302_group1.mapper.AuthorMapper;
import com.example.hsf302_group1.model.Author;
import com.example.hsf302_group1.repository.AuthorRepository;
import com.example.hsf302_group1.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepo;

    public AuthorServiceImpl(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public List<AuthorSearchDTO> getAllAuthors() {
        return authorRepo.findAll()
                .stream()
                .map(AuthorMapper::toSearchDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorSearchDTO> searchAuthorsByName(String keyword) {
        return authorRepo.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(AuthorMapper::toSearchDTO)
                .collect(Collectors.toList());
    }
}


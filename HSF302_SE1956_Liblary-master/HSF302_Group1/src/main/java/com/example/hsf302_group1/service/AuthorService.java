package com.example.hsf302_group1.service;

import com.example.hsf302_group1.dto.AuthorSearchDTO;
import java.util.List;

public interface AuthorService {
    List<AuthorSearchDTO> getAllAuthors();
    List<AuthorSearchDTO> searchAuthorsByName(String keyword);
}

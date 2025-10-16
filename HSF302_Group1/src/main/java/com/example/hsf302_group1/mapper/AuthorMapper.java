package com.example.hsf302_group1.mapper;

import com.example.hsf302_group1.dto.AuthorSearchDTO;
import com.example.hsf302_group1.model.Author;
import com.example.hsf302_group1.model.Book;


import java.util.stream.Collectors;

public class AuthorMapper {

    public static AuthorSearchDTO toSearchDTO(Author author) {
        AuthorSearchDTO dto = new AuthorSearchDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setDob(author.getDob());
        dto.setQuotes(author.getQuotes());

        if (author.getBookList() != null) {
            dto.setBookNames(
                    author.getBookList().stream()
                            .map(Book::getName)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

}

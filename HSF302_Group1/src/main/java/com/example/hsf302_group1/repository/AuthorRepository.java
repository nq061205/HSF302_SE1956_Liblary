package com.example.hsf302_group1.repository;

import com.example.hsf302_group1.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByNameContaining(String name);

}

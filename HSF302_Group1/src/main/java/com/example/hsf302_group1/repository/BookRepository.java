package com.example.hsf302_group1.repository;

import com.example.hsf302_group1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    Book getBookById(Integer id);

    List<Book> findByNameContainingIgnoreCase(String keyword);

    List<Book> findByTypeContainingIgnoreCase(String type);

    List<Book> findByPriceLessThan(double price);

}

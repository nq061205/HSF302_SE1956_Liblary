package com.example.hsf302_group1.repository;

import com.example.hsf302_group1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByNameContaining(String name);
    List<Book> findByType(String type);
    List<Book> findByPriceLessThan(double price);
    List<Book> findByPriceBetween(double minPrice, double maxPrice);


}

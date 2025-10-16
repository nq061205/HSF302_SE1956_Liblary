package com.example.hsf302_group1.repository;

import com.example.hsf302_group1.model.Book;
import com.example.hsf302_group1.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook,Integer> {
    List<BorrowBook> findByUserId(Integer userId);
    boolean existsByBookAndStatus(Book book, String status);

    List<BorrowBook> findByStatus(String status);
}

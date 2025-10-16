package com.example.hsf302_group1.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="borrowbooks")
public class BorrowBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "borrow_date")
    private Date dateBorrow;

    @Column(name="return_date")
    private Date dateReturned;

    @Column(name="status")
    private String status;

    public BorrowBook() {
    }
    public BorrowBook(Book book, User user,String status, Date dateBorrow, Date dateReturned) {
        this.book = book;
        this.user = user;
        this.status = status;
        this.dateBorrow = dateBorrow;
        this.dateReturned = dateReturned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(Date dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowBook{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", dateBorrow=" + dateBorrow +
                ", dateReturned=" + dateReturned +
                '}';
    }
}

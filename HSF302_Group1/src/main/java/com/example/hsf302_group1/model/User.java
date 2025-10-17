package com.example.hsf302_group1.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "balance")
    private double balance;
    @Column(name = "membership")
    private String membership;

    @Column(name="status")
    private boolean status;

    @Column(name = "membership_end_date")
    private Date membershipEndDate;

    @OneToMany(mappedBy = "user")
    private List<BorrowBook> borrowBooks;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<BorrowBook> getBorrowBooks() {
        return borrowBooks;
    }

    public void setBorrowBooks(List<BorrowBook> borrowBooks) {
        this.borrowBooks = borrowBooks;
    }

    public User() {
    }

    public User(int id, String name, boolean gender, Date dob, double balance, String membership, boolean status) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.balance = balance;
        this.membership = membership;
        this.status = status;
        this.membershipEndDate = Date.valueOf(LocalDate.now().plusMonths(1));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(Date membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", balance=" + balance +
                ", membership='" + membership + '\'' +
                '}';
    }
}

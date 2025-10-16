package com.example.hsf302_group1.dto;

import java.util.Date;

public class UserDTO {
    private int id;
    private String name;
    private boolean gender;
    private Date dob;
    private double balance;
    private String membership;
    private boolean active;

    public UserDTO() {
    }

    public UserDTO(int id, String name, boolean gender, Date dob, double balance, String membership, boolean active) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.balance = balance;
        this.membership = membership;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", balance=" + balance +
                ", membership='" + membership + '\'' +
                ", active=" + active +
                '}';
    }
}
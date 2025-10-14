package com.example.hsf302_group1.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="Managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="dob")
    private Date dob;

    @Column(name="phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name="library_id")
    private Library library;

    public Manager() {
    }

    public Manager(int id, String name, Date dob, String phone) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                '}';
    }
}

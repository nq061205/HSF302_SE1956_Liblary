package com.example.hsf302_group1.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="librarys")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private List<Manager> managerList;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private List<Book> bookList;

    public Library() {
    }

    public Library(String name) {
        this.name = name;
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
    @Override
    public String toString() {
        return "Library [id=" + id + ", name=" + name + "]";
    }
}

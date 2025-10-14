package com.example.hsf302_group1.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="dob")
    private String dob;

    @Column(name="quotes")
    private String quotes;

    @ManyToMany @JoinTable(name="book_author",joinColumns = @JoinColumn(name="author_id"),inverseJoinColumns = @JoinColumn(name="book_id"))
    private List<Book> bookList;


    public Author() {}
    public Author(int id, String name, String dob, String quotes) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.quotes = quotes;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", quotes='" + quotes + '\'' +
                '}';
    }
}

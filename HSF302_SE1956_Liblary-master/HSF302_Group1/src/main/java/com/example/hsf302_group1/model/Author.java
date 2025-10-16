package com.example.hsf302_group1.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dob")
    private String dob;

    @Column(name = "quotes", length = 500)
    private String quotes;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> bookList = new ArrayList<>();

    // ---------------- Constructors ----------------
    public Author() {
    }

    public Author(int id, String name, String dob, String quotes) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.quotes = quotes;
    }

    // ---------------- Getters & Setters ----------------
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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    // ---------------- Helper Methods ----------------
    /** Thêm một cuốn sách vào danh sách của tác giả */
    public void addBook(Book book) {
        if (!this.bookList.contains(book)) {
            this.bookList.add(book);
            // Nếu quan hệ 2 chiều, cần thêm dòng này:
            // book.getAuthorList().add(this);
        }
    }

    /** Xóa một cuốn sách khỏi danh sách của tác giả */
    public void removeBook(Book book) {
        if (this.bookList.contains(book)) {
            this.bookList.remove(book);
            // Nếu quan hệ 2 chiều, cần thêm dòng này:
            // book.getAuthorList().remove(this);
        }
    }

    // ---------------- ToString ----------------
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

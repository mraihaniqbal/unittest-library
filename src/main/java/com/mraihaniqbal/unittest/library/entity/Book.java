package com.mraihaniqbal.unittest.library.entity;

import com.mraihaniqbal.unittest.library.enums.Status;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private Integer isbn;

    private String title;
    private String author;

    @Enumerated(value = EnumType.STRING)
    private Status bookStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Status getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Status bookStatus) {
        this.bookStatus = bookStatus;
    }
}

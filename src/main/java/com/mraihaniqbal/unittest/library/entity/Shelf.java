package com.mraihaniqbal.unittest.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelf_id")
    private Long id;

    @Column(name = "max_capacity", length = 2)
    private Integer maxCapacity;

    @Column(name = "current_capacity", length = 2)
    private Integer currentCapacity;

    private String books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(Integer currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public List<Book> getBooks() throws IOException {
        if(books == null || books.isEmpty()){
            System.out.println("hai");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Book>> typeRef = new TypeReference<ArrayList<Book>>() {};
        return mapper.readValue(books, typeRef);
    }

    public void setBooks(String books) {
        this.books = books;
    }
}

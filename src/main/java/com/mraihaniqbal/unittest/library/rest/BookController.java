package com.mraihaniqbal.unittest.library.rest;

import com.mraihaniqbal.unittest.library.entity.Book;
import com.mraihaniqbal.unittest.library.enums.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @GetMapping("/books/{status}")
    public List<Book> findByStatus(@PathVariable Status status){
        return new ArrayList<>();
    }

}

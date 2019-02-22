package com.mraihaniqbal.unittest.library.rest;

import com.mraihaniqbal.unittest.library.entity.Book;
import com.mraihaniqbal.unittest.library.enums.Status;
import com.mraihaniqbal.unittest.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/list")
    public List<Book> findByStatus(
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(value = "title", required = false) String title){

        return bookService.findByParam(status,title);
    }

}

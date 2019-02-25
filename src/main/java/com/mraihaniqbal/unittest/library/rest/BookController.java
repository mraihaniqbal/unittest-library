package com.mraihaniqbal.unittest.library.rest;

import com.mraihaniqbal.unittest.library.entity.Book;
import com.mraihaniqbal.unittest.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/find")
    public List<Book> find(HttpServletRequest request){
        return bookService.findByParam(request);
    }

}

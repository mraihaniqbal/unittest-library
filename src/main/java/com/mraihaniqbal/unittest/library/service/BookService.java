package com.mraihaniqbal.unittest.library.service;

import com.mraihaniqbal.unittest.library.dao.BookDao;
import com.mraihaniqbal.unittest.library.entity.Book;
import com.mraihaniqbal.unittest.library.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> findByParam(Status status, String title){
        return bookDao.findByStatusOrTitle(status, title);
    }
}

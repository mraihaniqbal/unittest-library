package com.mraihaniqbal.unittest.library.service;

import com.mraihaniqbal.unittest.library.dao.BookDao;
import com.mraihaniqbal.unittest.library.entity.Book;
import com.mraihaniqbal.unittest.library.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class BookService {

    private BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> findByBookStatusAndTitle(Status status, String title){
        return bookDao.findByBookStatusAndTitle(status, title);
    }

    public List<Book> findByBookStatus(Status status){
        return bookDao.findByBookStatus(status);
    }

    public List<Book> findByParam(HttpServletRequest request){
        String title = request.getParameter("title");
        String statusString = request.getParameter("status");

        if(statusString != null){
            Status status = Status.valueOf(statusString);
            if(title != null){
                return this.findByBookStatusAndTitle(status,title);
            }

            return this.findByBookStatus(status);
        }else if (title != null){
            return bookDao.findByTitleStartingWith(title);
        }

        return bookDao.findAll();
    }
}

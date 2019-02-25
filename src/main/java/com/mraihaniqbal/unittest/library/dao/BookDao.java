package com.mraihaniqbal.unittest.library.dao;

import com.mraihaniqbal.unittest.library.entity.Book;
import com.mraihaniqbal.unittest.library.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book,Long> {

    List<Book> findByBookStatusAndTitle(Status bookStatus, String title);
    List<Book> findByBookStatus(Status bookStatus);
    List<Book> findByTitleStartingWith(String title);
}

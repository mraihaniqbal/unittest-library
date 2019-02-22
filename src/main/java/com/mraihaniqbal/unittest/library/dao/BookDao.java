package com.mraihaniqbal.unittest.library.dao;

import com.mraihaniqbal.unittest.library.entity.Book;
import com.mraihaniqbal.unittest.library.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book,Long> {

    @Query("from Book where status = ?1")
    List<Book> findByStatusOrTitle(Status status, String title);
}

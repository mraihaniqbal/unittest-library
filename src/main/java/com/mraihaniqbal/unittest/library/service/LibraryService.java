package com.mraihaniqbal.unittest.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mraihaniqbal.unittest.library.dao.BookDao;
import com.mraihaniqbal.unittest.library.dao.ShelfDao;
import com.mraihaniqbal.unittest.library.entity.Book;
import com.mraihaniqbal.unittest.library.entity.Shelf;
import com.mraihaniqbal.unittest.library.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class LibraryService {

    private Logger logger = LoggerFactory.getLogger(LibraryService.class);
    private ShelfDao shelfDao;
    private BookDao bookDao;

    @Autowired
    public LibraryService(ShelfDao shelfDao, BookDao bookDao) {
        this.shelfDao = shelfDao;
        this.bookDao = bookDao;
    }

    public List<Shelf> findAll(){
        return shelfDao.findAll();
    }

    public Shelf findById(Long id){
        Optional<Shelf> opt = shelfDao.findById(id);
        return opt.orElse(null);
    }

    public String addBook(Long id, Long bookId){
        Book book = bookDao.findById(bookId).orElse(null);
        Shelf shelf = this.findById(id);

        if(book == null || shelf == null){
            return "error null paramater";
        }

        Integer currentCapacity = shelf.getCurrentCapacity();
        if(currentCapacity.equals(shelf.getMaxCapacity())){
            return "full capacity";
        }

        try{
            List<Book> books = shelf.getBooks();
            if(books == null){
                books = new ArrayList<>();
            }
            //update book status
            book.setBookStatus(Status.SHELVED);

            books.add(book);
            String newBooks = convertFromList(books);

            //save new json string
            shelf.setBooks(newBooks);
            shelf.setCurrentCapacity(currentCapacity+1);

            shelfDao.save(shelf);
            bookDao.save(book);

        }catch (IOException io){
            logger.error(io.getMessage());
            return "error IO";
        }

        return "success";
    }

    public String removeBook(Long id, Long bookId){
        Book book = bookDao.findById(bookId).orElse(null);
        Shelf shelf = this.findById(id);

        if(book == null || shelf == null){
            return "error null";
        }else{
            try{
                List<Book> books = shelf.getBooks();
                if(books.isEmpty()){
                    return "empty book";
                }else{
                    books.removeIf(x -> x.getId().equals(bookId));
                    String updatedBooks = convertFromList(books);
                    shelf.setBooks(updatedBooks);
                    shelf.setCurrentCapacity(shelf.getCurrentCapacity()-1);
                    shelfDao.save(shelf);

                    book.setBookStatus(Status.NOT_SHELVED);
                    bookDao.save(book);
                }

            }catch (IOException io){
                logger.error(io.getMessage());
                return "error IO";
            }
        }

        return "success";
    }

    private String convertFromList(List<Book> books) throws IOException{
        //convert books to String
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, books);

        return new String(out.toByteArray());
    }
}

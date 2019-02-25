package com.mraihaniqbal.unittest.library.rest;

import com.mraihaniqbal.unittest.library.entity.Shelf;
import com.mraihaniqbal.unittest.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryController {

    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/library/shelf")
    public List<Shelf> findAllShelf(){
        return libraryService.findAll();
    }

    @GetMapping("/library/shelf/{id}")
    public Object findById(@PathVariable Long id){
        Shelf shelf = libraryService.findById(id);
        if(shelf == null){
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }

        return shelf;
    }

    @PostMapping("/library/{id}/add-book/{bookId}")
    public String addBook(@PathVariable(value = "id") Long id, @PathVariable(value = "bookId") Long bookId){
        return libraryService.addBook(id,bookId);
    }

    @PostMapping("/library/{id}/remove-book/{bookId}")
    public String removeBook(@PathVariable(value = "id") Long id, @PathVariable(value = "bookId") Long bookId){
        return libraryService.removeBook(id,bookId);
    }

}

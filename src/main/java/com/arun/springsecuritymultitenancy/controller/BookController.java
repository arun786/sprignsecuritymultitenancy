package com.arun.springsecuritymultitenancy.controller;

import com.arun.springsecuritymultitenancy.model.Book;
import com.arun.springsecuritymultitenancy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author arun on 9/23/20
 */

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/v1/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book bookById = bookService.getBookById(id);

        return ResponseEntity.ok(bookById);
    }

    @PostMapping("/v1/book")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book book1 = bookService.saveBook(book);

        return new ResponseEntity<>(book1, HttpStatus.CREATED);
    }

    @PutMapping("/v1/book/{id}")
    public ResponseEntity<HttpStatus> updateBook(@PathVariable Long id, @RequestBody Book book) throws ChangeSetPersister.NotFoundException {
        bookService.updateBook(id, book);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

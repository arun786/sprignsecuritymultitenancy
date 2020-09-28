package com.arun.springsecuritymultitenancy.service;

import com.arun.springsecuritymultitenancy.model.Book;

/**
 * @author arun on 9/23/20
 */

public interface BookService {

    Book getBookById(Long id);

    Book saveBook(Book book);

    void updateBook(Long id, Book book);


}

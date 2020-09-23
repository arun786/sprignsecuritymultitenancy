package com.arun.springsecuritymultitenancy.service;

import com.arun.springsecuritymultitenancy.model.Book;
import org.springframework.stereotype.Service;

/**
 * @author arun on 9/23/20
 */

@Service
public class BookServiceImpl implements BookService {

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public void saveBook(Book book) {

    }

    @Override
    public void updateBook(Long id, Book book) {

    }
}

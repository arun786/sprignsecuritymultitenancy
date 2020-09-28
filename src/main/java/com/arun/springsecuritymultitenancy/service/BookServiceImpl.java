package com.arun.springsecuritymultitenancy.service;

import com.arun.springsecuritymultitenancy.domain.BookDomain;
import com.arun.springsecuritymultitenancy.mapper.BookMapper;
import com.arun.springsecuritymultitenancy.model.Book;
import com.arun.springsecuritymultitenancy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author arun on 9/23/20
 */

@Service
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookById(Long id) {
        Optional<BookDomain> book = bookRepository.findById(id);
        return book.map(bookMapper::BookDomainToBook).orElse(null);

    }

    @Override
    public Book saveBook(Book book) {
        BookDomain bookDomain = bookMapper.bookToBookDomain(book);
        BookDomain save = bookRepository.save(bookDomain);
        return bookMapper.BookDomainToBook(save);

    }

    @Override
    public void updateBook(Long id, Book book) {

        Optional<BookDomain> bookDomain = bookRepository.findById(id);
        if (bookDomain.isPresent()) {
            if (bookDomain.get().getBookName().equals(book.getBookName())) {
                book.setId(id);
                bookRepository.save(bookMapper.bookToBookDomain(book));
            }
        }

        //TODO throw an exception if not found

    }
}

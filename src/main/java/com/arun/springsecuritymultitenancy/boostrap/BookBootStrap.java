package com.arun.springsecuritymultitenancy.boostrap;

import com.arun.springsecuritymultitenancy.domain.BookDomain;
import com.arun.springsecuritymultitenancy.mapper.BookMapper;
import com.arun.springsecuritymultitenancy.model.Book;
import com.arun.springsecuritymultitenancy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * @author arun on 9/26/20
 */

@Component
public class BookBootStrap implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookBootStrap(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public void run(String... args) {

        Book asTheCrowFlies = new Book()
                .setBookName("As the crow flies")
                .setAuthorName("Jeffrey Archer")
                .setPrice(new BigDecimal(10))
                .setVersion("1.0")
                .setQuantityOnHand(20)
                .setCreatedDate(OffsetDateTime.now())
                .setLastModifiedDate(OffsetDateTime.now());

        BookDomain bookDomain = bookMapper.bookToBookDomain(asTheCrowFlies);
        bookRepository.save(bookDomain);

        Book theProdigalDaughter = new Book()
                .setBookName("The Prodigal Daughter")
                .setAuthorName("Jeffrey Archer")
                .setPrice(new BigDecimal(20))
                .setVersion("1.0")
                .setQuantityOnHand(30)
                .setCreatedDate(OffsetDateTime.now())
                .setLastModifiedDate(OffsetDateTime.now());

        BookDomain theProdigalDaughterD = bookMapper.bookToBookDomain(theProdigalDaughter);
        bookRepository.save(theProdigalDaughterD);
    }
}

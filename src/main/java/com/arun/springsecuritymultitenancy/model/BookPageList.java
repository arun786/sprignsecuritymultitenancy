package com.arun.springsecuritymultitenancy.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author arun on 9/23/20
 */

public class BookPageList extends PageImpl<Book> {
    public BookPageList(List<Book> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BookPageList(List<Book> content) {
        super(content);
    }
}

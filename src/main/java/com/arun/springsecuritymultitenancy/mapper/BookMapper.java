package com.arun.springsecuritymultitenancy.mapper;

import com.arun.springsecuritymultitenancy.domain.BookDomain;
import com.arun.springsecuritymultitenancy.model.Book;
import org.mapstruct.Mapper;

/**
 * @author arun on 9/26/20
 */

@Mapper(uses = {DateMapper.class})
public interface BookMapper {

    Book BookDomainToBook(BookDomain bookDomain);

    BookDomain bookToBookDomain(Book book);


}

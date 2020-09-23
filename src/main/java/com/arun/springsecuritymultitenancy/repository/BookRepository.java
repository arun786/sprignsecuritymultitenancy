package com.arun.springsecuritymultitenancy.repository;

import com.arun.springsecuritymultitenancy.domain.BookDomain;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author arun on 9/23/20
 */

public interface BookRepository extends PagingAndSortingRepository<BookDomain, Long> {
}

package com.arun.springsecuritymultitenancy.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * @author arun on 9/23/20
 */


@Getter
@Setter
public class Book {
    private Long id;
    private String version;
    private OffsetDateTime createdDate = OffsetDateTime.now();
    private OffsetDateTime lastModifiedDate = OffsetDateTime.now();
    private String bookName;
    private BigDecimal price;
    private Integer quantityOnHand;
    private String authorName;
}

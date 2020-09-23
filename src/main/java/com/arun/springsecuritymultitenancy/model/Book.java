package com.arun.springsecuritymultitenancy.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author arun on 9/23/20
 */


@Getter
@Setter
public class Book {
    private Long id;
    private Integer version;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
    private String bookName;
    private BigDecimal price;
    private Integer quantityOnHand;
}

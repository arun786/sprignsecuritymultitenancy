package com.arun.springsecuritymultitenancy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author arun on 9/23/20
 */

@Getter
@Setter
@Entity(name = "Book")
public class BookDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer version;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private String bookName;
    private BigDecimal price;
    private Integer quantityOnHand;
}

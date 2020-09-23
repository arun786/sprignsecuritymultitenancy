# Spring Security Multi tenancy

Create a Model 

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


Page List from JPA

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


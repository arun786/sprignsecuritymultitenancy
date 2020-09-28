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


# Use of Mapstruct


## build.gradle configuration

task

    tasks.withType(JavaCompile){
        options.compilerArgs = [
                '-Amapstruct.defaultComponentModel=spring'
        ]
    }
    
    
dependencies to be added

    compile "org.mapstruct:mapstruct:${mapStructVersion}"
    annotationProcessor "org.mapstruct:mapstruct-processor:${mapStructVersion}"
    
    
interface for Mapper

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


    package com.arun.springsecuritymultitenancy.mapper;
    
    import org.springframework.stereotype.Component;
    
    import java.sql.Timestamp;
    import java.time.LocalDateTime;
    import java.time.OffsetDateTime;
    import java.time.ZoneOffset;
    
    /**
     * @author arun on 9/26/20
     */
    
    @Component
    public class DateMapper {
    
        public OffsetDateTime asOffsetDateTime(Timestamp ts) {
            if (ts != null) {
    
                LocalDateTime localDateTime = ts.toLocalDateTime();
                return OffsetDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue()
                        , localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute()
                        , localDateTime.getSecond(), localDateTime.getNano(), ZoneOffset.UTC);
            }
    
            return null;
        }
    
        public Timestamp asTimeStamp(OffsetDateTime offsetDateTime) {
            if (offsetDateTime != null) {
                return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
            }
    
            return null;
        }
    
    }


# Create a bootstrap which will enter 2 books at start up


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

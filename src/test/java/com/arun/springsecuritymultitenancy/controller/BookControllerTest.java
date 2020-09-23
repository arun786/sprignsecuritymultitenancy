package com.arun.springsecuritymultitenancy.controller;

import com.arun.springsecuritymultitenancy.model.Book;
import com.arun.springsecuritymultitenancy.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author arun on 9/23/20
 */

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;


    /**
     * Here spring security is applied for the test
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }


    /**
     * This is for Spring Security where we pass a mock user
     *
     * @throws Exception
     */
    @WithMockUser("spring")
    @Test
    void getBookById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/book/" + 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * For post method, for Security, with csrf is required
     *
     * @throws Exception
     */
    @Test
    void saveBook() throws Exception {
        Book book = new Book().setBookName("abc");
        String bookJson = objectMapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/book")
                .with(csrf())
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson)).andExpect(status().isCreated());
    }

    @Test
    void updateBook() throws Exception {
        Book book = new Book().setBookName("abc");
        String bookJson = objectMapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/book/" + 1)
                .with(csrf())
                .with(httpBasic("user", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson)).andExpect(status().isAccepted());
    }
}
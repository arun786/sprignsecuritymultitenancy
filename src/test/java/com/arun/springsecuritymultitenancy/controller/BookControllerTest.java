package com.arun.springsecuritymultitenancy.controller;

import com.arun.springsecuritymultitenancy.service.BookService;
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

    @Test
    void saveBook() {
    }

    @Test
    void updateBook() {
    }
}
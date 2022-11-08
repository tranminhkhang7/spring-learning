package com.example.bookscorner.controllers;

import com.example.bookscorner.config.CorsConfig;
import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.security.SecurityConfig;
import com.example.bookscorner.services.BookService;
import com.example.bookscorner.services.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(BookController.class)
@ContextConfiguration(classes = {SecurityConfig.class, CorsConfig.class})
@WebAppConfiguration
//@WebMvcTest(value = BookController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookServiceImpl bookServiceImpl;

    private BookResponseDto bookResponseDto;
    private List<BookResponseDto> bookResponseDtoList;

    @BeforeEach
    void beforeEach() throws Exception {
        bookResponseDto = BookResponseDto.builder()
                .bookId(5)
                .title("adarehwerjsdjh")
                .author("rt84738475")
                .price(12.0)
                .imageLink("abcd")
                .quantityLeft(20).build();
        bookResponseDtoList = new ArrayList<>();
        bookResponseDtoList.add(bookResponseDto);
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks_WhenValidDataRequest() throws Exception {
        int page = 0;
        int size = 1;
//        ResponseEntity<List<BookResponseDto>> expected =
//                new ResponseEntity<>(bookResponseDtoList, HttpStatus.OK);
        List<BookResponseDto> expected = bookResponseDtoList;

        when(bookServiceImpl.getAllBooks(page, size)).thenReturn(expected);

        MockHttpServletResponse actual = mockMvc.perform(get("/admin")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(status().isOk()).andReturn().getResponse();

        assertThat(actual.getContentAsString(), is("[\n" +
                "    {\n" +
                "        \"bookId\": 5,\n" +
                "        \"title\": \"adarehwerjsdjh\",\n" +
                "        \"author\": \"rt84738475\",\n" +
                "        \"price\": 12.0,\n" +
                "        \"imageLink\": \"abcd\",\n" +
                "        \"quantityLeft\": 20,\n" +
                "        \"status\": \"active\"\n" +
                "    }\n" +
                "]"));
    }
}

package com.example.bookscorner.controllers;

import com.example.bookscorner.dto.request.BookRequestDto;
import com.example.bookscorner.dto.request.BookRequestWithIdDto;
import com.example.bookscorner.dto.response.BookDetailResponseDto;
import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    ResponseEntity<List<BookResponseDto>> getBooksWithActiveStatus(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) List<String> genre) {
        return ResponseEntity.ok().body(bookService.getBooksWithActiveStatus(query, genre));
    }

    @GetMapping("/{bookId}")
    BookDetailResponseDto getBook(@PathVariable int bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping("/totalnumber")
    int countAllBooks() {
        return bookService.countAllBooks();
    }

    //Admin
    @PostMapping
    BookResponseDto addNewBook(@RequestBody BookRequestDto bookRequestDto) {
        return bookService.addNewBook(bookRequestDto);
    }

    //Admin
    @GetMapping("/allbooks")
    ResponseEntity<List<BookResponseDto>> getAllBooks(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "8") Integer size) {
        return ResponseEntity.ok().body(bookService.getAllBooks(page, size));
    }

    //Admin
    @PutMapping
    BookResponseDto updateBook(@RequestBody BookRequestWithIdDto bookRequestWithIdDto) {
        return bookService.updateBook(bookRequestWithIdDto);
    }
    //Admin
    @DeleteMapping("/{bookId}")
    ResponseEntity<ResponseDto> deleteBook(@PathVariable int bookId) {
        return bookService.deleteBook(bookId);
    }


}

package com.example.bookscorner.controllers;

import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/all") //to replace
    List<BookResponseDto> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping
    List<BookResponseDto> searchBooks(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) List<String> genre
    ) {
        return bookService.searchBooks(query, genre);
    }

    @PostMapping
    BookResponseDto addNewBook(@RequestBody Book book) {
        return bookService.addNewBook(book);
    }

    @PutMapping
    BookResponseDto updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping
    BookResponseDto deleteBook(@RequestBody Book book) {
        return bookService.deleteBook(book);
    }
}

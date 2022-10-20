package com.example.bookscorner.controllers;

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

    @GetMapping(path = "all")
    List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    void addNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }

    @PutMapping
    void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @DeleteMapping
    void deletaBook(@RequestBody Book book) {
        bookService.deleteBook(book);
    }
}

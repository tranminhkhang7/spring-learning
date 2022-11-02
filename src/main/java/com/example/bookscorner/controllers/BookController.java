package com.example.bookscorner.controllers;

import com.example.bookscorner.dto.request.BookRequestDto;
import com.example.bookscorner.dto.request.BookRequestWithIdDto;
import com.example.bookscorner.dto.response.BookResponseDto;
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
    ResponseEntity<List<BookResponseDto>> getBooks(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) List<String> genre) {
        return ResponseEntity.ok().body(bookService.getBooks(query, genre));
    }

//    @GetMapping // gom lại với api trên. ĐÃ SỬA
//    List<BookResponseDto> searchBooks(
//            @RequestParam(required = false) String query,
//            @RequestParam(required = false) List<String> genre
//    ) {
//        return bookService.searchBooks(query, genre);
//    }

    @GetMapping("/{bookId}")
    BookResponseDto getBook(@PathVariable int bookId) {
        return bookService.getBook(bookId);
    }

    @PostMapping
    BookResponseDto addNewBook(@RequestBody BookRequestDto bookRequestDto) {
        return bookService.addNewBook(bookRequestDto);
    }

    @PutMapping
    BookResponseDto updateBook(@RequestBody BookRequestWithIdDto bookRequestWithIdDto) {
        return bookService.updateBook(bookRequestWithIdDto);
    }
    @DeleteMapping
    BookResponseDto deleteBook(@RequestBody Book book) {
        return bookService.deleteBook(book);
    }
}

package com.example.bookscorner.services;

import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.entities.Book;

import java.util.List;

public interface BookService {

    public List<BookResponseDto> getBooks();

    public BookResponseDto addNewBook(Book book);

    public BookResponseDto updateBook(Book book);

    public BookResponseDto deleteBook(Book book);

    public List<BookResponseDto> searchBooks(String query, List<String> genre);
}

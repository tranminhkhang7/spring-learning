package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.BookRequestDto;
import com.example.bookscorner.dto.request.BookRequestWithIdDto;
import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.entities.Book;

import java.util.List;

public interface BookService {

    public List<BookResponseDto> getBooks(String query, List<String> genre);

    public BookResponseDto addNewBook(BookRequestDto bookRequestDto);

    public BookResponseDto updateBook(BookRequestWithIdDto bookRequestWithIdDto);

    public BookResponseDto deleteBook(Book book);

//    public List<BookResponseDto> searchBooks(String query, List<String> genre);

    BookResponseDto getBook(int bookId);
}

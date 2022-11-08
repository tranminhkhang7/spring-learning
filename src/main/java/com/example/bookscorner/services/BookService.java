package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.BookRequestDto;
import com.example.bookscorner.dto.request.BookRequestWithIdDto;
import com.example.bookscorner.dto.response.BookDetailResponseDto;
import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import com.example.bookscorner.entities.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    public List<BookResponseDto> getBooksWithActiveStatus(String query, List<String> genre);
    public List<BookResponseDto> getAllBooks(int page, int size);
    public BookDetailResponseDto getBook(int bookId);
    public BookResponseDto addNewBook(BookRequestDto bookRequestDto);
    public BookResponseDto updateBook(BookRequestWithIdDto bookRequestWithIdDto);
    public ResponseEntity<ResponseDto> deleteBook(int bookId);
    public int countAllBooks();
}

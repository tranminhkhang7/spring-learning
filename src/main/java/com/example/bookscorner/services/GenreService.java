package com.example.bookscorner.services;

import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.dto.response.GenreResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GenreService {
    public List<GenreResponseDto> getGenres(int page, int size);
    public Genre addNewGenre(Genre genre);
    public ResponseEntity<ResponseDto> deleteGenre(int genreId);
    public Genre updateGenre(int genreId, Genre genre);
    public List<BookResponseDto> getAllBooksByGenre(int genreId);
}

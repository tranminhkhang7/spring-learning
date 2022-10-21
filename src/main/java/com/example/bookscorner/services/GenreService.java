package com.example.bookscorner.services;

import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GenreService {
    public List<Genre> getGenres();
    public void addNewGenre(Genre genre);
    public void deleteGenre(int genreId);
    public void updateGenre(int genreId, Genre genre);
    public List<Book> getAllBooksByGenre(int genreId);
}

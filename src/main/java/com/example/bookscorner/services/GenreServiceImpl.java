package com.example.bookscorner.services;

import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService{
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        super();
        this.genreRepository = genreRepository;
    }

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public void addNewGenre(Genre genre) {
//        Optional<Genre> genreOptional = genreRepository
//                .findGenreByGenreName(genre.getGenreName());
//        if (genreOptional.isPresent()) {
//            throw new IllegalStateException("The genre is existing");
//        }
        boolean exists = genreRepository.existsById(genre.getGenreId());
        if (exists) {
            throw new IllegalStateException("The genre is existing");
        }
        genreRepository.save(genre);
    }

    public void updateGenre(int genreId, Genre genre) {
        boolean exists = genreRepository.existsById(genreId);
        if (!exists) {
            throw new IllegalStateException("The genre you want to update does not exist");
        }

        Genre genreOld = genreRepository.findById(genreId).get();

        genreOld.setGenreName(genre.getGenreName());

        genreRepository.save(genreOld);
    }

    public void deleteGenre(int genreId) {
        boolean exists = genreRepository.existsById(genreId);
        if (!exists) {
             throw new IllegalStateException("The genre does not  exist");
        }
        genreRepository.deleteById(genreId);
    }

    public List<Book> getAllBooksByGenre(int genreId) {
        boolean exists = genreRepository.existsById(genreId);
        if (!exists) {
            throw new IllegalStateException("The genre does not  exist");
        }
//        Genre genre = genreRepository.findById(genreId).get();
        return genreRepository.getAllBooksByGenre(genreId);
    }
}
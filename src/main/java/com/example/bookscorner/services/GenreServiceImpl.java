package com.example.bookscorner.services;

import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.repositories.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{
    // edit this if there's a bug
    @Autowired
    private final GenreRepository genreRepository;
    @Autowired
    private final ModelMapper mapper;
//    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, ModelMapper mapper) {
        super();
        this.genreRepository = genreRepository;
        this.mapper = mapper;
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

    public List<BookResponseDto> getAllBooksByGenre(int genreId) {
        boolean exists = genreRepository.existsById(genreId);
        if (!exists) {
            throw new IllegalStateException("The genre does not exist");
        }
        List<Book> bookEntitiesByGenre = genreRepository.getAllBooksByGenre(genreId);
        List<BookResponseDto> bookDtoByGenre = new ArrayList<>();

        for (Book bookEntity: bookEntitiesByGenre) {
            BookResponseDto bookResponseDto = mapper.map(bookEntity, BookResponseDto.class);
            bookDtoByGenre.add(bookResponseDto);
        }
        return bookDtoByGenre;
    }
}
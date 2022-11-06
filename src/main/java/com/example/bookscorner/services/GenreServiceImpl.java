package com.example.bookscorner.services;

import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.dto.response.GenreResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.mappers.GenreEntityAndGenreResponseDtoMapper;
import com.example.bookscorner.repositories.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{
    // edit this if there's a bug
    @Autowired
    private final GenreRepository genreRepository;
    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private final GenreEntityAndGenreResponseDtoMapper genreEntityAndGenreResponseDtoMapper;
//    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, ModelMapper mapper, GenreEntityAndGenreResponseDtoMapper genreEntityAndGenreResponseDtoMapper) {
        super();
        this.genreRepository = genreRepository;
        this.mapper = mapper;
        this.genreEntityAndGenreResponseDtoMapper = genreEntityAndGenreResponseDtoMapper;
    }

    public List<GenreResponseDto> getGenres() {
        List<Genre> genreList = genreRepository.findAll();
        List<GenreResponseDto> genreResponseDtoList = genreEntityAndGenreResponseDtoMapper.mapToDto(genreList);
        return genreResponseDtoList;
    }

    public Genre addNewGenre(Genre genre) {
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

        return genre;
    }

    public Genre updateGenre(int genreId, Genre genre) {
        boolean exists = genreRepository.existsById(genreId);
        if (!exists) {
            throw new IllegalStateException("The genre you want to update does not exist");
        }

        Genre genreOld = genreRepository.findById(genreId).get();

        genreOld.setGenreName(genre.getGenreName());

        genreRepository.save(genreOld);

        return genreOld;
    }

    public ResponseEntity<ResponseDto> deleteGenre(int genreId) {
        boolean exists = genreRepository.existsById(genreId);
        if (!exists) {
             throw new IllegalStateException("The genre does not  exist");
        }
        genreRepository.deleteById(genreId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(null,
                        "Successfully delete the item.",
                        "200"));
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
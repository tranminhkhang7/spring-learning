package com.example.bookscorner.mappers;

import com.example.bookscorner.dto.response.CommentResponseDto;
import com.example.bookscorner.dto.response.GenreResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Comment;
import com.example.bookscorner.entities.Customer;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.repositories.BookRepository;
import com.example.bookscorner.repositories.CustomerRepository;
import com.example.bookscorner.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreEntityAndGenreResponseDtoMapper {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    // Entity to dto
    public GenreResponseDto mapToDto(Genre genreEntity) {
        GenreResponseDto genreResponseDto = new GenreResponseDto();

        genreResponseDto.setGenreId(genreEntity.getGenreId());
        genreResponseDto.setGenreName(genreEntity.getGenreName());

        return genreResponseDto;
    }

    // List Entity to List dto
    public List<GenreResponseDto> mapToDto(List<Genre> genreEntityList) {
        List<GenreResponseDto> genreResponseDtoList = new ArrayList<>();
        for (Genre genreEntity: genreEntityList) {
            GenreResponseDto genreResponseDto = new GenreResponseDto();

            genreResponseDto.setGenreId(genreEntity.getGenreId());
            genreResponseDto.setGenreName(genreEntity.getGenreName());

            genreResponseDtoList.add(genreResponseDto);
        }

        return genreResponseDtoList;
    }



//    // Maps from do to entity
//    public Genre mapToEntity( genreEntity) {
//        GenreResponseDto genreResponseDto = new GenreResponseDto();
//
//        genreResponseDto.setGenreId(genreEntity.getGenreId());
//        genreResponseDto.setGenreName(genreEntity.getGenreName());
//    }

}

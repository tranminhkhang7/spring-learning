package com.example.bookscorner.controllers;

import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "genre")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    List<Genre> getGenre() {
        return genreService.getGenres();
    }

    @GetMapping("/{genreId}")
    List<BookResponseDto> getAllBooksByGenre(@PathVariable("genreId") int genreId) {
        return genreService.getAllBooksByGenre(genreId);
    }

    @PostMapping // phải return object json. ĐÃ SỬA
    Genre addNewGenre(@RequestBody Genre genre) {
        return genreService.addNewGenre(genre);
    }

    @PutMapping(path = "{genreId}") // phải return object json, chưa đối tượng. ĐÃ SỬA
    Genre updateGenre(@PathVariable("genreId") int genreId, @RequestBody Genre genre) {
        return genreService.updateGenre(genreId, genre);
    }
 
    @DeleteMapping(path = "{genreId}") // phải return object json, chứa message. ĐÃ SỬA
    ResponseEntity<ResponseDto> deleteGenre(@PathVariable("genreId") int genreId){
        return genreService.deleteGenre(genreId);
    }
}

package com.example.bookscorner.controllers;

import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    void addNewGenre(@RequestBody Genre genre) {
        genreService.addNewGenre(genre);
    }

    @PutMapping(path = "{genreId}")
    void updateGenre(@PathVariable("genreId") int genreId, @RequestBody Genre genre) {
        System.out.println("helloupdate");
        genreService.updateGenre(genreId, genre);
    }

    @DeleteMapping(path = "{genreId}")
    void deleteGenre(@PathVariable("genreId") int genreId){
        genreService.deleteGenre(genreId);
    }
}

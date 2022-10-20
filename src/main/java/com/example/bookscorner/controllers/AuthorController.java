package com.example.bookscorner.controllers;

import com.example.bookscorner.entities.Author;
import com.example.bookscorner.entities.Genre;
import com.example.bookscorner.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    List<Author> getAuthor() {
        return authorService.getAuthors();
    }

    @PostMapping
    void addNewAuthor(@RequestBody Genre genre) {
//        authorService.addNewGenre(genre);
    }

}

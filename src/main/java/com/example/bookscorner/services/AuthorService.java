package com.example.bookscorner.services;

import com.example.bookscorner.entities.Author;
import com.example.bookscorner.entities.Genre;

import java.util.List;

public interface AuthorService {
    public List<Author> getAuthors();
}

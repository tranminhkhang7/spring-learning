package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query ("SELECT g FROM Genre g WHERE g.genreName = ?1")
    Optional<Genre> findGenreByGenreName(String genreName);
}

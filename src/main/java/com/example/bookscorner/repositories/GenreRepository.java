package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
//    @Query ("SELECT g FROM Genre g WHERE g.genreName = ?1")
//    Optional<Genre> findGenreByGenreName(String genreName);

//    @Query ("SELECT b FROM Book b JOIN BookGenre bg ON b.bookId = bg.book.bookId WHERE bg.genre.genreId = ?1")

    @Query ("SELECT b FROM Book b JOIN BookGenre bg ON b.bookId = bg.book.bookId WHERE bg.genre.genreId = :genreId")
    List<Book> getAllBooksByGenre (@Param("genreId") int genreId);
    public Genre findGenreByGenreId(int genreId);
    public List<Genre> findAllByOrderByGenreId(Pageable pageable);
}

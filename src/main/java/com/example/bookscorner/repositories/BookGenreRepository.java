package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, Integer> {
    void deleteBookGenresByBook_BookId(int bookId);

    List<BookGenre> findAllByBook_BookId(int bookId);
}

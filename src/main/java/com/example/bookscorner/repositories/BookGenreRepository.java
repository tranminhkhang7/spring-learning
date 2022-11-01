package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, Integer> {
    void deleteBookGenresByBook_BookId(int bookId);
}

package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

//    @Query("SELECT b FROM Book b JOIN BookGenre bg ON b.bookId = bg.book.bookId WHERE b.title LIKE %?1%")
//    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1% ")

//    List<Book> findBooksByTitleContainingIgnoreCaseAnd(String query)
    @Query("SELECT b " +
            "FROM Book b JOIN BookGenre bg ON b.bookId = bg.book.bookId " +
            "WHERE b.title LIKE %?1% " +
            "AND bg.genre.genreId IN ?2")
    List<Book> searchBooks(String query, List<Integer> genre);

}
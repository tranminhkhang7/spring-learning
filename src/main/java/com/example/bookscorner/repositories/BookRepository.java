package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
            "AND bg.genre.genreId IN ?2 " +
            "AND b.status = ?3")
    public List<Book> searchBooks(String query, List<Integer> genre, String status);

    @Query("SELECT b " +
            "FROM Book b JOIN BookGenre bg ON b.bookId = bg.book.bookId " +
            "WHERE b.title LIKE %?1% " +
            "AND b.status = ?2")
    public List<Book> searchBooks(String query, String status);
    public Book findBookByBookId(int bookId);
    public List<Book> findAllByOrderByBookIdAsc(Pageable pageable);
    public List<Book> findAllByStatus(String status);
    public int countAllByBookIdGreaterThan(int bookId);
}

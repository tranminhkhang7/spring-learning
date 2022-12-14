package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findCommentByCustomer_CustomerIdAndBook_BookId(int customerId, int bookId);

//    @Query("SELECT c " +
//            "FROM Comment c " +
//            "WHERE c.book.bookId = ?1")
//    List<Comment> findCommentsWithBookId(int bookId);
    List<Comment> findAllByBook_BookId(int bookId);
}

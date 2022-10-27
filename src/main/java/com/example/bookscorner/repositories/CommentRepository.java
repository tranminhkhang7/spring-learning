package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findCommentByCustomer_CustomerIdAndBook_BookId(int customerId, int bookId);
}

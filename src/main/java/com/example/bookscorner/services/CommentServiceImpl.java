package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.CommentRequestDto;
import com.example.bookscorner.dto.response.CommentResponseDto;
import com.example.bookscorner.entities.Comment;
import com.example.bookscorner.mappers.CommentEntityAndCommentRequestDtoMapper;
import com.example.bookscorner.mappers.CommentEntityAndCommentResponseDtoMapper;
import com.example.bookscorner.repositories.BookRepository;
import com.example.bookscorner.repositories.CommentRepository;
import com.example.bookscorner.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentEntityAndCommentRequestDtoMapper entityAndRequestDtoMapper;

    @Autowired
    private CommentEntityAndCommentResponseDtoMapper entityAndResponseDtoMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CustomerRepository customerRepository, BookRepository bookRepository, CommentEntityAndCommentRequestDtoMapper entityAndRequestDtoMapper, CommentEntityAndCommentResponseDtoMapper entityAndResponseDtoMapper) {
        this.commentRepository = commentRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.entityAndRequestDtoMapper = entityAndRequestDtoMapper;
        this.entityAndResponseDtoMapper = entityAndResponseDtoMapper;
    }

    public CommentResponseDto addComment(CommentRequestDto commentRequestDto) {
        int customerId = commentRequestDto.getCustomerId();
        int bookId = commentRequestDto.getBookId();
        if (customerRepository.findCustomerByCustomerId(customerId) == null) {
            throw new IllegalStateException("This customer does not exist.");
        }
        if (bookRepository.findBookByBookId(bookId) == null) {
            throw new IllegalStateException("This book does not exist.");
        }
        if (commentRepository.findCommentByCustomer_CustomerIdAndBook_BookId(customerId, bookId) != null) {
            throw new IllegalStateException("This customer have commented this book.");
        }

        Comment comment = new Comment();
        entityAndRequestDtoMapper.map(commentRequestDto, comment);

        commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto();
        entityAndResponseDtoMapper.map(comment, commentResponseDto);
        return commentResponseDto;
    }
}

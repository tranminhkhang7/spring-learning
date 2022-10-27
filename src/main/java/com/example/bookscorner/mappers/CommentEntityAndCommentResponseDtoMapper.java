package com.example.bookscorner.mappers;

import com.example.bookscorner.dto.response.CartResponseDto;
import com.example.bookscorner.dto.response.CommentResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Cart;
import com.example.bookscorner.entities.Comment;
import com.example.bookscorner.entities.Customer;
import com.example.bookscorner.repositories.BookRepository;
import com.example.bookscorner.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityAndCommentResponseDtoMapper {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    // Maps from entity response dto
    public void map(Comment commentEntity, CommentResponseDto commentResponseDto) {
        BeanUtils.copyProperties(commentEntity, commentResponseDto);

        if (commentEntity.getCustomer() != null) {
            commentResponseDto.setCustomerId(commentEntity.getCustomer().getCustomerId());
        }
        if (commentEntity.getBook() != null) {
            commentResponseDto.setBookId(commentEntity.getBook().getBookId());
        }
    }

    // Maps from do to entity
    public void map(CommentResponseDto commentResponseDto, Comment commentEntity) {
        BeanUtils.copyProperties(commentResponseDto, commentEntity);


        int customerId = commentResponseDto.getCustomerId();
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        commentEntity.setCustomer(customer);

        int bookId = commentResponseDto.getBookId();
        Book book = bookRepository.findBookByBookId(bookId);
        commentEntity.setBook(book);
    }
}

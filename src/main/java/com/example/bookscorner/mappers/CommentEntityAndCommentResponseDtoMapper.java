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

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentEntityAndCommentResponseDtoMapper {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    // Entity to dto
    public CommentResponseDto mapToDto(Comment commentEntity) {
        CommentResponseDto commentResponseDto = new CommentResponseDto();

//        BeanUtils.copyProperties(commentEntity, commentResponseDto);
        commentResponseDto.setCommentId(commentEntity.getCommentId());
        commentResponseDto.setTimestamp(commentEntity.getTimestamp());
        commentResponseDto.setContent(commentEntity.getContent());
        commentResponseDto.setRating(commentEntity.getRating());

        if (commentEntity.getCustomer() != null) {
            commentResponseDto.setCustomerId(commentEntity.getCustomer().getCustomerId());
        }
        if (commentEntity.getContent() != null) {
            commentResponseDto.setCustomerName(commentEntity.getCustomer().getName());
        }
        if (commentEntity.getBook() != null) {
            commentResponseDto.setBookId(commentEntity.getBook().getBookId());
        }

        return commentResponseDto;
    }

    // Maps from do to entity
    public Comment mapToEntity(CommentResponseDto commentResponseDto) {
        Comment commentEntity = new Comment();

//        BeanUtils.copyProperties(commentResponseDto, commentEntity);

        commentEntity.setCommentId(commentResponseDto.getCommentId());
        commentEntity.setTimestamp(commentResponseDto.getTimestamp());
        commentEntity.setContent(commentResponseDto.getContent());
        commentEntity.setRating(commentResponseDto.getRating());

        int customerId = commentResponseDto.getCustomerId();
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        commentEntity.setCustomer(customer);

        int bookId = commentResponseDto.getBookId();
        Book book = bookRepository.findBookByBookId(bookId);
        commentEntity.setBook(book);

        return commentEntity;
    }

    // entity list to dto list
    public List<CommentResponseDto> mapToListDto(List<Comment> commentEntityList) {
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for (Comment commentEntity: commentEntityList) {
            CommentResponseDto commentResponseDto = new CommentResponseDto();
//            BeanUtils.copyProperties(commentEntity, commentResponseDto);

            commentResponseDto.setCommentId(commentEntity.getCommentId());
            commentResponseDto.setTimestamp(commentEntity.getTimestamp());
            commentResponseDto.setContent(commentEntity.getContent());
            commentResponseDto.setRating(commentEntity.getRating());

            if (commentEntity.getCustomer() != null) {
                commentResponseDto.setCustomerId(commentEntity.getCustomer().getCustomerId());
            }
            if (commentEntity.getContent() != null) {
                commentResponseDto.setCustomerName(commentEntity.getCustomer().getName());
            }
            if (commentEntity.getBook() != null) {
                commentResponseDto.setBookId(commentEntity.getBook().getBookId());
            }
            commentResponseDtoList.add(commentResponseDto);
        }

        return commentResponseDtoList;
    }
}

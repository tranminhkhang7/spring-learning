package com.example.bookscorner.mappers;

import com.example.bookscorner.dto.request.CartRequestDto;
import com.example.bookscorner.dto.request.CommentRequestDto;
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
public class CommentEntityAndCommentRequestDtoMapper {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    // Entity to Dto
    public CommentRequestDto mapToDto(Comment commentEntity) {
        CommentRequestDto commentRequestDto = new CommentRequestDto();

//        BeanUtils.copyProperties(commentEntity, commentRequestDto);

        commentRequestDto.setTimestamp(commentEntity.getTimestamp());
        commentRequestDto.setContent(commentEntity.getContent());

        if (commentEntity.getCustomer() != null) {
            commentRequestDto.setCustomerId(commentEntity.getCustomer().getCustomerId());
        }
        if (commentEntity.getBook() != null) {
            commentRequestDto.setBookId(commentEntity.getBook().getBookId());
        }

        return commentRequestDto;
    }

    // Dto to Entity
    public Comment mapToEntity(CommentRequestDto commentRequestDto) {
        Comment commentEntity = new Comment();
//        BeanUtils.copyProperties(commentEntity, commentRequestDto);

        commentEntity.setTimestamp(commentRequestDto.getTimestamp());
        commentEntity.setContent(commentRequestDto.getContent());

        int customerId = commentRequestDto.getCustomerId();
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        commentEntity.setCustomer(customer);

        int bookId = commentRequestDto.getBookId();
        Book book = bookRepository.findBookByBookId(bookId);
        commentEntity.setBook(book);

        return commentEntity;
    }


}

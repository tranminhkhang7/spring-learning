package com.example.bookscorner.mappers;

import com.example.bookscorner.dto.request.CartRequestDto;
import com.example.bookscorner.dto.response.CartResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Cart;
import com.example.bookscorner.entities.Customer;
import com.example.bookscorner.repositories.BookRepository;
import com.example.bookscorner.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartEntityAndCartResponseDtoMapper {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookRepository bookRepository;

    // Maps from entity to dto
    public void map(Cart cartEntity, CartResponseDto cartResponseDto) {
        BeanUtils.copyProperties(cartEntity, cartResponseDto);

        if (cartEntity.getCustomer() != null) {
            cartResponseDto.setCustomerId(cartEntity.getCustomer().getCustomerId());
        }
        if (cartEntity.getBook() != null) {
            cartResponseDto.setBookId(cartEntity.getBook().getBookId());
        }
    }

    // Maps from do to entity
    public void map(CartResponseDto cartResponseDto, Cart cartEntity) {
        BeanUtils.copyProperties(cartResponseDto, cartEntity);


        int customerId = cartResponseDto.getCustomerId();
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        cartEntity.setCustomer(customer);

        int bookId = cartResponseDto.getBookId();
        Book book = bookRepository.findBookByBookId(bookId);
        cartEntity.setBook(book);
    }
}

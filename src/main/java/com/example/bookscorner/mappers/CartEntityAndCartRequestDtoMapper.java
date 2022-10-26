package com.example.bookscorner.mappers;

import com.example.bookscorner.dto.request.CartRequestDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Cart;
import com.example.bookscorner.entities.Customer;
import com.example.bookscorner.repositories.BookRepository;
import com.example.bookscorner.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartEntityAndCartRequestDtoMapper {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookRepository bookRepository;

    // Maps from entity to dto
    public void map(Cart cartEntity, CartRequestDto cartRequestDto) {
        BeanUtils.copyProperties(cartEntity, cartRequestDto);

        if (cartEntity.getCustomer() != null) {
            cartRequestDto.setCustomerId(cartEntity.getCustomer().getCustomerId());
        }
        if (cartEntity.getBook() != null) {
            cartRequestDto.setBookId(cartEntity.getBook().getBookId());
        }
    }

    // Maps from do to entity
    public void map(CartRequestDto cartRequestDto, Cart cartEntity) {
        BeanUtils.copyProperties(cartRequestDto, cartEntity);


        int customerId = cartRequestDto.getCustomerId();
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        cartEntity.setCustomer(customer);

        int bookId = cartRequestDto.getBookId();
        Book book = bookRepository.findBookByBookId(bookId);
        cartEntity.setBook(book);
    }
}

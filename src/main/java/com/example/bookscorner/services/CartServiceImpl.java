package com.example.bookscorner.services;

import com.example.bookscorner.dto.response.BookResponseDto;
import com.example.bookscorner.dto.response.CartDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Cart;
import com.example.bookscorner.repositories.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired // move this to next to the constructor if there are bugs
    private final CartRepository cartRepository;

    @Autowired
    private final ModelMapper mapper;

    public CartServiceImpl(CartRepository cartRepository, ModelMapper mapper) {
        this.cartRepository = cartRepository;
        this.mapper = mapper;
    }

    public List<CartDto> getBooksInCart() {
        int customerId = 1;
        List<Cart> cartList = cartRepository.findByCustomer_CustomerId(customerId);


        List<CartDto> cartDtoList = Cart.toCartDto(cartList);

        return cartDtoList;
//        return null;
    }

//    public List<Cart> getBooksInCart() {
//        int customerId = 1;
//        List<Cart> cartList = cartRepository.findByCustomer_CustomerId(customerId);
//
////        System.out.println(cartList);
//
////        List<CartDto> cartDtoList = Cart.toCartDto(cartList);
//
////        System.out.println(cartDtoList);
//
//        return cartList;
////        return null;
//    }

}

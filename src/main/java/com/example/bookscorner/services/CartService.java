package com.example.bookscorner.services;

import com.example.bookscorner.dto.response.CartDto;
import com.example.bookscorner.entities.Cart;

import java.util.List;

public interface CartService {
    List<CartDto> getBooksInCart();

//    List<Cart> getBooksInCart();
}

package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.CartRequestDto;

import java.util.List;

public interface CartService {
    List<com.example.bookscorner.dto.response.CartResponseDto> getBooksInCart();

    com.example.bookscorner.dto.response.CartResponseDto updateABookInCart(CartRequestDto cartRequestDto);
}

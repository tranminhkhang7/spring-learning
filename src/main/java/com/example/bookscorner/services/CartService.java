package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.CartRequestDto;
import com.example.bookscorner.dto.response.CartResponseDto;

import java.util.List;

public interface CartService {
    List<CartResponseDto> getBooksInCart();

    CartResponseDto updateABookInCart(CartRequestDto cartRequestDto);

    String deleteABookInCart(CartRequestDto cartRequestDto);
}

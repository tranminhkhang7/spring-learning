package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.CartRequestDto;
import com.example.bookscorner.dto.response.CartResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    List<CartResponseDto> getBooksInCart();

    CartResponseDto updateABookInCart(CartRequestDto cartRequestDto);

    ResponseEntity<ResponseDto> deleteABookInCart(CartRequestDto cartRequestDto);
}

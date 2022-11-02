package com.example.bookscorner.controllers;

import com.example.bookscorner.dto.request.CartRequestDto;
import com.example.bookscorner.dto.response.CartResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import com.example.bookscorner.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "checkout")
public class CartController {
    @Autowired
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

//    @GetMapping
//    public ResponseEntity<List<CartResponseDto>> getBooksInCart() {
//        return ResponseEntity.ok().body(cartService.getBooksInCart());
//    }

    @PutMapping
    CartResponseDto updateABookInCart(@Valid @RequestBody CartRequestDto cartRequestDto) {
        return cartService.updateABookInCart(cartRequestDto);
    }

    @DeleteMapping // trả về Object JSON. ĐÃ SỬA
    ResponseEntity<ResponseDto> deleteABookInCart(@Valid @RequestBody CartRequestDto cartRequestDto) {
        return cartService.deleteABookInCart(cartRequestDto);
    }
}

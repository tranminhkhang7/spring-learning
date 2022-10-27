package com.example.bookscorner.controllers;

import com.example.bookscorner.dto.request.CartRequestDto;
import com.example.bookscorner.dto.response.CartResponseDto;
import com.example.bookscorner.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    List<CartResponseDto> getBooksInCart() {
        return cartService.getBooksInCart();
    }

    @PutMapping
    CartResponseDto updateABookInCart(@Valid @RequestBody CartRequestDto cartRequestDto) {
        return cartService.updateABookInCart(cartRequestDto);
    }

    @DeleteMapping
    String deleteABookInCart(@Valid @RequestBody CartRequestDto cartRequestDto) {
        return cartService.deleteABookInCart(cartRequestDto);
    }
}

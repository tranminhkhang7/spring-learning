package com.example.bookscorner.controllers;

import com.example.bookscorner.dto.response.CartDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Cart;
import com.example.bookscorner.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    List<CartDto> getBooksInCart() {
        return cartService.getBooksInCart();
    }

//    @PutMapping(path = "{bookId}")
//    void updateABookInCart(@PathVariable int bookId,
//                        @RequestBody Cart cart) {
//        cartService.
//    }

}

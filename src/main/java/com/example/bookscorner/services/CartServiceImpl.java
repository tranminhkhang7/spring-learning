package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.CartRequestDto;
import com.example.bookscorner.dto.response.CartResponseDto;
import com.example.bookscorner.entities.Cart;
import com.example.bookscorner.mappers.CartEntityAndCartRequestDtoMapper;
import com.example.bookscorner.mappers.CartEntityAndCartResponseDtoMapper;
import com.example.bookscorner.repositories.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired // move this to next to the constructor if there are bugs
    private final CartRepository cartRepository;

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    CartEntityAndCartRequestDtoMapper cartEntityAndCartRequestDtoMapper;

    @Autowired
    CartEntityAndCartResponseDtoMapper cartEntityAndCartResponseDtoMapper;

    public CartServiceImpl(CartRepository cartRepository, ModelMapper mapper) {
        this.cartRepository = cartRepository;
        this.mapper = mapper;
    }

    public List<CartResponseDto> getBooksInCart() {
        int customerId = 2;
        List<Cart> cartList = cartRepository.findByCustomer_CustomerId(customerId);

        List<com.example.bookscorner.dto.response.CartResponseDto> cartResponseDtoList = Cart.toCartDto(cartList);

        return cartResponseDtoList;
    }

    public CartResponseDto updateABookInCart(CartRequestDto cartRequestDto) {
        int customerId = cartRequestDto.getCustomerId();
        int bookId = cartRequestDto.getBookId();

        List<Cart> cartFound = cartRepository.findByCustomer_CustomerIdAndBook_BookId(customerId, bookId);

        if (cartFound.isEmpty()) { //If the book is not in the cart, then add the book to cart
            Cart cart = new Cart();
            this.cartEntityAndCartRequestDtoMapper.map(cartRequestDto, cart);
//            if (cartRequestDto.getQuantity() > cart.getBook().getQuantityLeft()) {
//
//            }
            cartRepository.save(cart);

            CartResponseDto cartResponseDto = new CartResponseDto();
            this.cartEntityAndCartResponseDtoMapper.map(cart, cartResponseDto);

            return cartResponseDto;
        } else {
            Cart cart = cartFound.get(0);
            cart.setQuantity(cart.getQuantity() + cartRequestDto.getQuantity());
            cartRepository.save(cart);

            CartResponseDto cartResponseDto = new CartResponseDto();
            this.cartEntityAndCartResponseDtoMapper.map(cart, cartResponseDto);

            return cartResponseDto;
        }
    }

}

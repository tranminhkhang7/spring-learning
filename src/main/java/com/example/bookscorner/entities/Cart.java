package com.example.bookscorner.entities;

import com.example.bookscorner.dto.response.CartDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @SequenceGenerator(
            name = "carts_sequence",
            sequenceName = "cards_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cards_sequence"
    )
    private int cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @Column(name = "quantity")
    int quantity;

    public static CartDto toCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getCartId());
        cartDto.setBookId(cart.book.getBookId());
        cartDto.setCustomerId(cart.customer.getCustomerId());
        cartDto.setQuantity(cart.getQuantity());
        return cartDto;
    }

    public static List<CartDto> toCartDto(List<Cart> cartList) {
        List<CartDto> cartDtoList = new ArrayList<>();
        for (Cart cart: cartList) {
            CartDto cartDto = new CartDto();
            cartDto.setCartId(cart.getCartId());
            cartDto.setBookId(cart.book.getBookId());
            cartDto.setCustomerId(cart.customer.getCustomerId());
            cartDto.setQuantity(cart.getQuantity());
            cartDtoList.add(cartDto);
        }
        return cartDtoList;
    }
}

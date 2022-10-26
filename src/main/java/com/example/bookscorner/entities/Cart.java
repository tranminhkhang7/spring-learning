package com.example.bookscorner.entities;

import com.example.bookscorner.dto.response.CartResponseDto;
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

    public static CartResponseDto toCartDto(Cart cart) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setCartId(cart.getCartId());
        cartResponseDto.setBookId(cart.book.getBookId());
        cartResponseDto.setCustomerId(cart.customer.getCustomerId());
        cartResponseDto.setQuantity(cart.getQuantity());
        return cartResponseDto;
    }

    public static List<CartResponseDto> toCartDto(List<Cart> cartList) {
        List<CartResponseDto> cartResponseDtoList = new ArrayList<>();
        for (Cart cart: cartList) {
            CartResponseDto cartResponseDto = new CartResponseDto();
            cartResponseDto.setCartId(cart.getCartId());
            cartResponseDto.setBookId(cart.book.getBookId());
            cartResponseDto.setCustomerId(cart.customer.getCustomerId());
            cartResponseDto.setQuantity(cart.getQuantity());
            cartResponseDtoList.add(cartResponseDto);
        }
        return cartResponseDtoList;
    }
}

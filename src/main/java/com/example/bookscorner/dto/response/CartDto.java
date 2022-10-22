package com.example.bookscorner.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDto {
    public int cartId;
    public int customerId;
    public int bookId;
    public int quantity;
}

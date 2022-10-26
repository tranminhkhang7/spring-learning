package com.example.bookscorner.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartRequestDto {
    public int customerId;
    public int bookId;
    public int quantity;
}

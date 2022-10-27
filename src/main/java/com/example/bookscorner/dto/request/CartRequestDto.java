package com.example.bookscorner.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDto {
    @NotNull(message = "Customer ID is mandatory.")
    public int customerId;
    @NotNull(message = "Book ID is mandatory.")
    public int bookId;
    @NotNull(message = "Quantity is mandatory.")
    public int quantity;
}

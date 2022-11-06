package com.example.bookscorner.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private int bookId;
    private String title;
    private String author;
    private double price;
    private String imageLink;
    private int quantityLeft;
    private String status;
}

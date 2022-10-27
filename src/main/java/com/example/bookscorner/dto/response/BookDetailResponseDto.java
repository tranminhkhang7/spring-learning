package com.example.bookscorner.dto.response;

import com.example.bookscorner.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailResponseDto {
    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private double price;
    private String imageLink;
    private String description;
    private int quantityLeft;
    private List<Comment> bookComments;
}

package com.example.bookscorner.dto.request;

import com.example.bookscorner.entities.BookGenre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// This DTO is now for:
//      + update an existing book
public class BookRequestWithIdDto {
    private int bookId;
    private String title;
    private String author;
    private String status;
    private String publisher;
    private double price;
    private String imageLink;
    private String description;
    private int quantityLeft;
    private List<Integer> bookGenres;
}

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
//      + add a new book,
//      + update an existing book
public class BookRequestDto {
    private String title;
    private String author;
    private String status;
    private String publisher;
    private double price;
    private String imageLink;
    private String description;
    private int quantityLeft;
    private List<BookGenre> bookGenres;

//    @OneToMany(mappedBy = "book")
//    private List<Cart> cart;
//
//    @OneToMany(mappedBy = "book")
//    private List<Cart> bookComments;
//
//    @OneToMany(mappedBy = "book")
//    private List<Cart> BookFavourite;
//
//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
//    private List<OrderDetail> orderDetails;
//
//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
//    private List<BookGenre> bookGenres;

}

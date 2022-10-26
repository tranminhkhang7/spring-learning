package com.example.bookscorner.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "books")
public class Book {
    @Id
    @SequenceGenerator(
            name = "books_sequence",
            sequenceName = "books_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "books_sequence"
    )
    private int bookId;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "status", length = 55)
    private String status;

    @Column(name = "publisher", length = 255)
    private String publisher;

    @Column(name = "price")
    private double price;

    @Column(name = "image_link", length = 511)
    private String imageLink;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "average_rate")
    private double averageRate;

    @Column(name = "count_rate")
    private int countRate;

    @Column(name = "quantity_left")
    private int quantityLeft;

    @OneToMany(mappedBy = "book")
    private List<Cart> cart;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookGenre> bookGenres;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookAuthor> bookAuthors;
}
//viết unitest thì viết cho service và controller
//nếu ko đủ thời gian thì có thể viết một vài service và controller tiêu biểu

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

    @Column(name = "avg_rate")
    private double avgRate;

    @Column(name = "cnt_rate")
    private int cntRate;

    @Column(name = "quantity_left")
    private int quantityLeft;

    @OneToMany(mappedBy = "book")
    private List<Cart> cart; //xoá private nếu bug

    @OneToMany(mappedBy = "book")
    private List<OrderDetail> orderDetails; //xoá private nếu bug
}

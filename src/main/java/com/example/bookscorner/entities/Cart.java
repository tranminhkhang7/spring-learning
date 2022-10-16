package com.example.bookscorner.entities;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @Column(name = "quantity")
    int quantity;
}

package com.example.bookscorner.entities;

import com.example.bookscorner.dto.response.CartResponseDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @Column(name = "quantity")
    int quantity;
}

package com.example.bookscorner.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favourites")
public class Favourite {
    @Id
    @SequenceGenerator(
            name = "favourites_sequence",
            sequenceName = "favourites_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "favourites_sequence"
    )
    @Column(name = "favourite_id")
    private int favouriteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;

    //    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;
}

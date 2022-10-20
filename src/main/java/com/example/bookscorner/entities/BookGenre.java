package com.example.bookscorner.entities;

import javax.persistence.*;

@Entity
@Table(name = "book_genre")
public class BookGenre {
    @Id
    @SequenceGenerator(
            name = "book_genre_sequence",
            sequenceName = "book_genre_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_genre_sequence"
    )
    @Column(name = "book_genre_id")
    private int bookGenreId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}

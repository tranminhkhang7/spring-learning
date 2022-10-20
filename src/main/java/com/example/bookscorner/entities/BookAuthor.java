package com.example.bookscorner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book_author")
public class BookAuthor {
    @Id
    @SequenceGenerator(
            name = "book_author_sequence",
            sequenceName = "book_author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_author_sequence"
    )
    @Column(name = "book_genre_id")
    private int bookAuthorId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}

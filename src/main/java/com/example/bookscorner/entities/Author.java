package com.example.bookscorner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @SequenceGenerator(
            name = "authors_sequence",
            sequenceName = "authors_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "authors_sequence"
    )
    @Column(name = "author_id")
    private int authorId;

    @Column(name = "author_name", length = 255)
    private String authorName;

    @OneToMany(mappedBy = "author")
    private List<BookAuthor> bookAuthors;
}

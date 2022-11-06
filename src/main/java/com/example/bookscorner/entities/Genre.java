package com.example.bookscorner.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @SequenceGenerator(
            name = "genres_sequence",
            sequenceName = "genres_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "genres_sequence"
    )
    @Column(name = "genre_id")
//    @JsonIgnore
    private int genreId;

    @Column(name = "genre_name", length = 55)
    private String genreName;

    @OneToMany(mappedBy = "genre")
    private List<BookGenre> bookGenres;

    public Genre(String genreName) {
        this.genreName = genreName;
    }
}

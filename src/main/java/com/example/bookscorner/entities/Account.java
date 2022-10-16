package com.example.bookscorner.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @SequenceGenerator(
            name = "accounts_sequence",
            sequenceName = "accounts_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "accounts_sequence"
    )
    private int accountId;

    @Column(length = 255)
    private String email;

    @Column(length = 55)
    private String password;

    @OneToOne(mappedBy = "mAccount", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;
}

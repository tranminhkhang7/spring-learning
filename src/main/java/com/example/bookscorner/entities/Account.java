package com.example.bookscorner.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @Column(length = 255, unique = true)
    private String email;

    @Column(length = 255)
    private String password;

//    @Column(length = 55)
//    private String role;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToOne(mappedBy = "mAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Customer customer;
}

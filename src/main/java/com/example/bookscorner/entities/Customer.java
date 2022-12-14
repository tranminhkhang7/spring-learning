package com.example.bookscorner.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private int customerId;

//    @JoinColumn(name = "customer_id")
    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Account account;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "status", length = 55)
    private String status;

    @Column(name = "gender", length = 55)
    private String gender;

    @Column(name = "birthday")
    private Date birthday;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    List<Cart> cart;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    List<Comment> comment;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    List<Favourite> favourite;

    @OneToMany(mappedBy = "customer")
    List<Order> order;
}

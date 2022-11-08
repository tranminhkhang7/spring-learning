package com.example.bookscorner.entities;

import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(
            name = "orders_sequence",
            sequenceName = "orders_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_sequence"
    )
    private int orderId;

    @Column(name = "time")
    private Timestamp timestamp;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetail;
}


//[
//        {
//        "bookId":5,
//        "title":"Educated",
//        "imageLink":"https://firebasestorage.googleapis.com/v0/b/spring-react-learning.appspot.com/o/img2.jpeg?alt=media&token=83f0f704-606a-4ccd-be5c-02f3e063917d",
//        "price":0,
//        "quantity":2,
//        "id":1
//        },
//        {
//        "id":2,
//        "bookId":4,
//        "price":0,
//        "imageLink":"https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1595674533l/23692271._SY475_.jpg",
//        "title":null,
//        "quantity":2
//        },
//        {
//        "bookId":10,
//        "title":"A Promised Land",
//        "imageLink":"https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1600357110l/55361205._SY475_.jpg",
//        "price":35.65,
//        "quantity":1,
//        "id":3
//        }
//        ]
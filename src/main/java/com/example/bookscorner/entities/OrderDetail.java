package com.example.bookscorner.entities;

import lombok.*;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @SequenceGenerator(
            name = "order_details_sequence",
            sequenceName = "order_details_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_details_sequence"
    )
    private int orderDetailsId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; // bỏ private nếu gặp lỗi

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book; //bỏ private nếu gặp lỗi

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;
}

package com.example.bookscorner.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderResponseDto {
    private int orderId;
    private Timestamp timestamp;
    private String phone;
    private String address;
    private double totalAmount;
    private int customerId;
}

package com.example.bookscorner.dto.request;

import com.example.bookscorner.entities.Customer;
import com.example.bookscorner.entities.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderRequestDto {
    private Timestamp timestamp;
    private String phone;
    private String address;
    private double totalAmount;
    private int customerId;
    private List<OrderDetailRequestDto> orderDetail;
}

package com.example.bookscorner.dto.response;

import com.example.bookscorner.entities.Account;
import com.example.bookscorner.entities.Cart;
import com.example.bookscorner.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {
    private int customerId;
    private String name;
    private String gender;
    private Date birthday;
    private String status;
}

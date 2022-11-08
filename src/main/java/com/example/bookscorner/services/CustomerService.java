package com.example.bookscorner.services;

import com.example.bookscorner.dto.response.CustomerResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    public List<CustomerResponseDto> getAllCustomers(int page, int size);

    public int countAllCustomer();

    public ResponseEntity<ResponseDto> banCustomer(int customerId);
}

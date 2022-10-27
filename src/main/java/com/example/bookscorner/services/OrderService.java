package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.OrderRequestDto;
import com.example.bookscorner.dto.response.OrderResponseDto;

public interface OrderService {
    OrderResponseDto addOrder(OrderRequestDto orderRequestDto);
}

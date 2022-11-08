package com.example.bookscorner.controllers;

import com.example.bookscorner.dto.request.OrderRequestDto;
import com.example.bookscorner.dto.response.OrderResponseDto;
import com.example.bookscorner.entities.Order;
import com.example.bookscorner.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService; // thêm final?

    @PostMapping
    OrderResponseDto addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.addOrder(orderRequestDto);
    }



}

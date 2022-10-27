package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.OrderRequestDto;
import com.example.bookscorner.dto.response.OrderResponseDto;
import com.example.bookscorner.entities.Order;
import com.example.bookscorner.mappers.OrderEntityAndOrderRequestDtoMapper;
import com.example.bookscorner.mappers.OrderEntityAndOrderResponseDtoMapper;
import com.example.bookscorner.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderEntityAndOrderRequestDtoMapper orderEntityAndOrderRequestDtoMapper;

    @Autowired
    private OrderEntityAndOrderResponseDtoMapper orderEntityAndOrderResponseDtoMapper;
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        orderEntityAndOrderRequestDtoMapper.map(orderRequestDto, order);

        orderRepository.save(order);

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderEntityAndOrderResponseDtoMapper.map(order, orderResponseDto);
        return orderResponseDto;
    }
}

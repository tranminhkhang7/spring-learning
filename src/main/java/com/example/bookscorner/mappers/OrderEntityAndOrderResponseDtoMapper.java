package com.example.bookscorner.mappers;

import com.example.bookscorner.dto.response.CommentResponseDto;
import com.example.bookscorner.dto.response.OrderResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Comment;
import com.example.bookscorner.entities.Customer;
import com.example.bookscorner.entities.Order;
import com.example.bookscorner.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityAndOrderResponseDtoMapper {
    @Autowired
    private CustomerRepository customerRepository;

    // Maps from entity response dto
    public void map(Order orderEntity, OrderResponseDto orderResponseDto) {
        BeanUtils.copyProperties(orderEntity, orderResponseDto);

        if (orderEntity.getCustomer() != null) {
            orderResponseDto.setCustomerId(orderEntity.getCustomer().getCustomerId());
        }

    }

    // Maps from do to entity
    public void map(OrderResponseDto orderResponseDto, Order orderEntity) {
        BeanUtils.copyProperties(orderResponseDto, orderEntity);

        int customerId = orderResponseDto.getCustomerId();
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        orderEntity.setCustomer(customer);
    }
}

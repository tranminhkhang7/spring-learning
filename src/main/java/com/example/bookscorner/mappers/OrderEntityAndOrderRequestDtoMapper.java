package com.example.bookscorner.mappers;

import com.example.bookscorner.dto.request.CommentRequestDto;
import com.example.bookscorner.dto.request.OrderRequestDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Comment;
import com.example.bookscorner.entities.Customer;
import com.example.bookscorner.entities.Order;
import com.example.bookscorner.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityAndOrderRequestDtoMapper {
    @Autowired
    private CustomerRepository customerRepository;

    // Maps from entity to request dto
    public void map(Order orderEntity, OrderRequestDto orderRequestDto) {
        BeanUtils.copyProperties(orderEntity, orderRequestDto);

        if (orderEntity.getCustomer() != null) {
            orderRequestDto.setCustomerId(orderEntity.getCustomer().getCustomerId());
        }
    }

    // Maps from request dto to entity
    public void map(OrderRequestDto orderRequestDto, Order orderEntity) {
        BeanUtils.copyProperties(orderRequestDto, orderEntity);

        int customerId = orderRequestDto.getCustomerId();
        Customer customer = customerRepository.findCustomerByCustomerId(customerId);
        orderEntity.setCustomer(customer);
    }
}

package com.example.bookscorner.services;

import com.example.bookscorner.dto.request.OrderDetailRequestDto;
import com.example.bookscorner.dto.request.OrderRequestDto;
import com.example.bookscorner.dto.response.OrderResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Order;
import com.example.bookscorner.entities.OrderDetail;
import com.example.bookscorner.exceptions.NotFoundException;
import com.example.bookscorner.mappers.OrderEntityAndOrderRequestDtoMapper;
import com.example.bookscorner.mappers.OrderEntityAndOrderResponseDtoMapper;
import com.example.bookscorner.repositories.BookRepository;
import com.example.bookscorner.repositories.OrderDetailRepository;
import com.example.bookscorner.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderEntityAndOrderRequestDtoMapper orderEntityAndOrderRequestDtoMapper;

    @Autowired
    private OrderEntityAndOrderResponseDtoMapper orderEntityAndOrderResponseDtoMapper;
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        orderEntityAndOrderRequestDtoMapper.map(orderRequestDto, order);

        orderRepository.save(order);

        for (OrderDetailRequestDto orderDetailRequestDto: orderRequestDto.getOrderDetail()) {
            int bookId = orderDetailRequestDto.getBookId();
            Book book = bookRepository.findBookByBookId(bookId);
            if (book == null) {
                throw new NotFoundException("The book you want to order does not exist");
            }
            int quantity = orderDetailRequestDto.getQuantity();
            if (quantity > book.getQuantityLeft()) {
                throw new NotFoundException("The quantity you want to order is greater than quantity left");
            }
            double price = book.getPrice();

            OrderDetail orderDetail = new OrderDetail(order, book, quantity, price);
            orderDetailRepository.save(orderDetail);

            book.setQuantityLeft(book.getQuantityLeft() - quantity);
            bookRepository.save(book);
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderEntityAndOrderResponseDtoMapper.map(order, orderResponseDto);
        return orderResponseDto;
    }
}

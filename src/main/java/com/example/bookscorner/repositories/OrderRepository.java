package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
//    Order findByOrderId(int orderId);
}

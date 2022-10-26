package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByCustomer_CustomerId(int customerId);

    List<Cart> findByCustomer_CustomerIdAndBook_BookId(int customerId, int bookId);

    void deleteCartByCartId(int cartId);
}

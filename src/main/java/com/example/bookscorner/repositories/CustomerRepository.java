package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByCustomerId(int customerId);


    Customer findByAccount_Email(String email);
}

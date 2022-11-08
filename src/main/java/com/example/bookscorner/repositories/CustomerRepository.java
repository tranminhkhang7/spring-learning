package com.example.bookscorner.repositories;

import com.example.bookscorner.entities.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByCustomerId(int customerId);
    Customer findByAccount_Email(String email);
    public List<Customer> findAllByOrderByCustomerId(Pageable pageable);
    public int countAllByCustomerIdGreaterThan(int customerId);

}

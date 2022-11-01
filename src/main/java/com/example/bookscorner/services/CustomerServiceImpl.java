package com.example.bookscorner.services;

import com.example.bookscorner.entities.Customer;
import com.example.bookscorner.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    public String returnUserNameByEmail(String email) {
//        Customer customer = customerRepository.findCustomerByMAccount_Email(email);
//        return customer.getName();
//    }
}

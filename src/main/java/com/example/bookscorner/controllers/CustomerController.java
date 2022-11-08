package com.example.bookscorner.controllers;

import com.example.bookscorner.dto.response.CustomerResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import com.example.bookscorner.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/admin")
    public List<CustomerResponseDto> getAllCustomers(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "8") Integer size) {
        return customerService.getAllCustomers(page, size);
    }

    @DeleteMapping("/admin/{customerId}")
    ResponseEntity<ResponseDto> banCustomer(@PathVariable int customerId) {
        return customerService.banCustomer(customerId);
    }


    @GetMapping("/totalnumber")
    int countAllCustomer() {
        return customerService.countAllCustomer();
    }

}

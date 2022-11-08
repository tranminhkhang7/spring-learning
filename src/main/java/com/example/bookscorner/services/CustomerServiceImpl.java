package com.example.bookscorner.services;

import com.example.bookscorner.dto.response.CustomerResponseDto;
import com.example.bookscorner.dto.response.ResponseDto;
import com.example.bookscorner.entities.Book;
import com.example.bookscorner.entities.Customer;
import com.example.bookscorner.exceptions.NotFoundException;
import com.example.bookscorner.mappers.ObjectMapperUtils;
import com.example.bookscorner.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final ObjectMapperUtils objectMapperUtils;

    public CustomerServiceImpl(CustomerRepository customerRepository, ObjectMapperUtils objectMapperUtils) {
        this.customerRepository = customerRepository;
        this.objectMapperUtils = objectMapperUtils;
    }

    public List<CustomerResponseDto> getAllCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Customer> customerList = customerRepository.findAllByOrderByCustomerId(pageable);

        List<CustomerResponseDto> customerResponseDtoList =
                objectMapperUtils.mapAll(customerList, CustomerResponseDto.class);

        return customerResponseDtoList;
    }

    public int countAllCustomer() {
        return customerRepository.countAllByCustomerIdGreaterThan(-1);
    }

    public ResponseEntity<ResponseDto> banCustomer(int customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new NotFoundException("The customer you want to ban does not exist");
        }

        Customer customerOld = customerRepository.findCustomerByCustomerId(customerId);

        if (customerOld.getStatus().trim().equals("active")) {
            customerOld.setStatus("disabled");
        } else {
            customerOld.setStatus("active");
        }


        customerRepository.save(customerOld);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Successfully change the status of the customer.","200"));

    }
}

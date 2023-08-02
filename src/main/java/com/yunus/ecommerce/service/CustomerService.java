package com.yunus.ecommerce.service;

import com.yunus.ecommerce.dto.CustomerDtos.CustomerDto;
import com.yunus.ecommerce.dto.CustomerDtos.CustomerCreateDto;
import com.yunus.ecommerce.dto.CustomerDtos.CustomerUpdateDto;

import java.util.List;

public interface CustomerService {
    CustomerDto getCustomerById(long customerId);
    List<CustomerDto> getCustomers();
    CustomerDto createCustomer(CustomerCreateDto customerCreateDto);
    CustomerDto updateCustomer(CustomerUpdateDto customerUpdateDto);
    String  deleteCustomer(long customerId);
}

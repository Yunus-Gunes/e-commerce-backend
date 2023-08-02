package com.yunus.ecommerce.controller;

import com.yunus.ecommerce.dto.CustomerDtos.CustomerCreateDto;
import com.yunus.ecommerce.dto.CustomerDtos.CustomerDto;
import com.yunus.ecommerce.dto.CustomerDtos.CustomerUpdateDto;
import com.yunus.ecommerce.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomerById(@PathVariable long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping("/customers")
    public List<CustomerDto> getCustomers(){
        return customerService.getCustomers();
    }


    @PostMapping("/create")
    public CustomerDto createAuthor(@RequestBody CustomerCreateDto customerCreateDto) {
        return customerService.createCustomer(customerCreateDto);
    }

    @PutMapping("/update")
    public CustomerDto updateAuthor(@RequestBody CustomerUpdateDto customerUpdateDto) {
        return customerService.updateCustomer(customerUpdateDto);
    }

    @DeleteMapping("/delete")
    public String  deleteAuthor(@RequestParam long customerId) {
        return customerService.deleteCustomer(customerId);
    }

}

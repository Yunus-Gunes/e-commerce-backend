package com.yunus.ecommerce.service.impl;

import com.yunus.ecommerce.dto.CustomerDtos.CustomerDto;
import com.yunus.ecommerce.dto.CustomerDtos.CustomerCreateDto;
import com.yunus.ecommerce.dto.CustomerDtos.CustomerUpdateDto;
import com.yunus.ecommerce.entity.Customer;
import com.yunus.ecommerce.repository.CustomerRepository;
import com.yunus.ecommerce.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    private final CustomerRepository customerRepository;
    
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }
    
    public CustomerDto getCustomerById(long customerId){
        String errorMessage = "Customer does not exist";
        Customer customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new DataIntegrityViolationException(errorMessage));

        return this.modelMapper.map(customer, CustomerDto.class) ;
    }


    public List<CustomerDto> getCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer,CustomerDto.class))
                .collect(Collectors.toList());
    }


    public CustomerDto createCustomer(CustomerCreateDto customerCreateDto){
        Customer customer = new Customer();
        customer.setCustomerName(customerCreateDto.getCustomerName());
        customer.setCustomerEmail(customerCreateDto.getCustomerEmail());
        customer.setCustomerPassword(customerCreateDto.getCustomerPassword());
        customer.setCustomerAddress(customerCreateDto.getCustomerAddress());

        try{
            customerRepository.save(customer);
            return this.modelMapper.map(customer,CustomerDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Failed to create customer.";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public CustomerDto updateCustomer(CustomerUpdateDto customerUpdateDto){
        CustomerDto customerDto = getCustomerById(customerUpdateDto.getCustomerId());
        Customer customer = this.modelMapper.map(customerDto, Customer.class);

        try {
            customer.setCustomerName(customerUpdateDto.getCustomerName());
            customer.setCustomerEmail(customerUpdateDto.getCustomerEmail());
            customer.setCustomerPassword(customerUpdateDto.getCustomerPassword());
            customer.setCustomerAddress(customerUpdateDto.getCustomerAddress());

            customerRepository.save(customer);

            return this.modelMapper.map(customer,CustomerDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Customer failed to update";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public String  deleteCustomer(long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isPresent() ){
            customerRepository.deleteById(customerId);
            return "The customer deleted.";
        }else {
            return "Customer not found.";
        }
    }
    
}

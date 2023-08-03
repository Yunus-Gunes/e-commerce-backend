package com.yunus.ecommerce.service.impl;


import com.yunus.ecommerce.dto.OrderDtos.OrderCreateDto;
import com.yunus.ecommerce.dto.OrderDtos.OrderDto;
import com.yunus.ecommerce.dto.OrderDtos.OrderUpdateDto;
import com.yunus.ecommerce.entity.Customer;
import com.yunus.ecommerce.entity.Order;
import com.yunus.ecommerce.entity.OrderStatus;
import com.yunus.ecommerce.repository.CategoryRepository;
import com.yunus.ecommerce.repository.CustomerRepository;
import com.yunus.ecommerce.repository.OrderRepository;
import com.yunus.ecommerce.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, CategoryRepository categoryRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;

    }

    public OrderDto getOrderById(long orderId){
        String errorMessage = "Order does not exist";
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new DataIntegrityViolationException(errorMessage));

        return this.modelMapper.map(order,OrderDto.class) ;
    }

    public List<OrderDto> getOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order,OrderDto.class))
                .collect(Collectors.toList());
    }

    
    public OrderDto createOrder(OrderCreateDto orderCreateDto){
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setOrderTotal(orderCreateDto.getOrderTotal());
        Customer customer = customerRepository.getReferenceById(orderCreateDto.getCustomerId());
        order.setCustomer(customer);

        try{
            orderRepository.save(order);
            return this.modelMapper.map(order,OrderDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Failed to create order.";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public OrderDto updateOrder(OrderUpdateDto orderUpdateDto){
        OrderDto orderDto = getOrderById(orderUpdateDto.getOrderId());
        Order order = this.modelMapper.map(orderDto, Order.class);

        try {

            order.setOrderTotal(orderUpdateDto.getOrderTotal());
            order.setOrderStatus(orderUpdateDto.getOrderStatus());

            orderRepository.save(order);

            return this.modelMapper.map(order,OrderDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Order failed to update";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public String  deleteOrder(long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isPresent() ){
            orderRepository.deleteById(orderId);
            return "The order deleted.";
        }else {
            return "Order not found.";
        }
    }

    public OrderDto updateOrderStatus(long orderId, OrderStatus orderStatus){
        OrderDto orderDto = getOrderById(orderId);
        Order order = this.modelMapper.map(orderDto, Order.class);

        try {
            order.setOrderStatus(orderStatus);
            orderRepository.save(order);

            return this.modelMapper.map(order,OrderDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Order failed to update";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    


}

package com.yunus.ecommerce.service.impl;


import com.yunus.ecommerce.dto.OrderDtos.OrderDto;
import com.yunus.ecommerce.dto.OrderDtos.OrderUpdateDto;
import com.yunus.ecommerce.entity.*;
import com.yunus.ecommerce.repository.*;
import com.yunus.ecommerce.service.OrderService;
import jakarta.transaction.Transactional;
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
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderDetailRepository orderDetailRepository, BasketRepository basketRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
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

    @Transactional
    public OrderDto createOrder(Long userId){
        Order order = new Order();

        order.setOrderDate(LocalDateTime.now());

        User user = userRepository.getReferenceById(userId);
        order.setUser(user);

        //order.setOrderTotal(orderCreateDto.getOrderTotal());

        try{
            orderRepository.save(order);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Failed to create order.";
            throw new DataIntegrityViolationException(errorMessage);
        }


        List<Basket> basketProduct = basketRepository.findByUserId(userId);

        float orderTotalPrice = 0.0F;
        for (Basket basketElemenet: basketProduct) {
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setNumberOfProducts(basketElemenet.getNumberOfProducts());

            Float basketOrderPrice = Float.valueOf(productRepository.findProductPrice(basketElemenet.getBasketProduct().getProductId()));
            //Basketten productId alır product tabledan price alır
            orderDetail.setOrderProductPrice(basketOrderPrice);

            orderTotalPrice += basketOrderPrice*basketElemenet.getNumberOfProducts();
            orderDetail.setOrderDetailOrderId(order);

            orderDetail.setOrderProduct(productRepository.getReferenceById(basketElemenet.getBasketProduct().getProductId()));

            orderDetailRepository.save(orderDetail);
        }

        order.setOrderTotal(orderTotalPrice);

        try{
            orderRepository.save(order);


            //Basketde kullanıcıya ait satırları sil
            basketRepository.deleteBasketByUserId(userId);

        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Failed to create order.";
            throw new DataIntegrityViolationException(errorMessage);
        }
        return this.modelMapper.map(order,OrderDto.class);

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

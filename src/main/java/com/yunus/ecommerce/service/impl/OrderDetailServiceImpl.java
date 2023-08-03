package com.yunus.ecommerce.service.impl;


import com.yunus.ecommerce.dto.OrderDetailDtos.OrderDetailCreateDto;
import com.yunus.ecommerce.dto.OrderDetailDtos.OrderDetailDto;
import com.yunus.ecommerce.dto.OrderDetailDtos.OrderDetailUpdateDto;
import com.yunus.ecommerce.entity.Order;
import com.yunus.ecommerce.entity.OrderDetail;
import com.yunus.ecommerce.entity.Product;
import com.yunus.ecommerce.repository.*;
import com.yunus.ecommerce.service.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailsRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public OrderDetailServiceImpl(OrderDetailsRepository orderDetailRepository, OrderRepository orderRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;

    }


    public OrderDetailDto getOrderDetailById(long orderDetailId){
        String errorMessage = "OrderDetail does not exist";
        OrderDetail orderDetail = this.orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new DataIntegrityViolationException(errorMessage));

        return this.modelMapper.map(orderDetail,OrderDetailDto.class);
        //return orderDetailMapper.orderDetailToOrderDetailDto(orderDetail);
    }

    public List<OrderDetailDto> getOrderDetails(){
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream()
                .map(orderDetail -> modelMapper.map(orderDetail,OrderDetailDto.class))
                .collect(Collectors.toList());
    }
    
    public OrderDetailDto createOrderDetail(OrderDetailCreateDto orderDetailCreateDto){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setSubTotal(orderDetailCreateDto.getSubTotal());
        orderDetail.setProductPrice(orderDetailCreateDto.getProductPrice());

        Order order = orderRepository.getReferenceById(orderDetailCreateDto.getOrderId());
        orderDetail.setOrderDetailOrder(order);

        Product product = productRepository.getReferenceById(orderDetailCreateDto.getProductId());
        orderDetail.setOrderDetailProduct(product);

        try{
            orderDetailRepository.save(orderDetail);
            return this.modelMapper.map(orderDetail,OrderDetailDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Failed to create orderDetail.";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public OrderDetailDto updateOrderDetail(OrderDetailUpdateDto orderDetailUpdateDto){
        OrderDetailDto orderDetailDto = getOrderDetailById(orderDetailUpdateDto.getOrderDetailId());
        OrderDetail orderDetail = this.modelMapper.map(orderDetailDto, OrderDetail.class);

        try {
            orderDetail.setSubTotal(orderDetailUpdateDto.getSubTotal());
            orderDetail.setProductPrice(orderDetailUpdateDto.getProductPrice());

            orderDetailRepository.save(orderDetail);

            return this.modelMapper.map(orderDetail,OrderDetailDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "OrderDetail failed to update";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public String  deleteOrderDetail(long orderDetailId) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(orderDetailId);

        if(orderDetail.isPresent() ){
            orderDetailRepository.deleteById(orderDetailId);
            return "The orderDetail deleted.";
        }else {
            return "OrderDetail not found.";
        }
    }

    


}

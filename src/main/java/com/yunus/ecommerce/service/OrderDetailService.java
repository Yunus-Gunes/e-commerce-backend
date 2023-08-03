package com.yunus.ecommerce.service;

import com.yunus.ecommerce.dto.OrderDetailDtos.OrderDetailCreateDto;
import com.yunus.ecommerce.dto.OrderDetailDtos.OrderDetailDto;
import com.yunus.ecommerce.dto.OrderDetailDtos.OrderDetailUpdateDto;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDto getOrderDetailById(long orderDetailId);
    List<OrderDetailDto> getOrderDetails();
    OrderDetailDto createOrderDetail(OrderDetailCreateDto orderDetailCreateDto);
    OrderDetailDto updateOrderDetail(OrderDetailUpdateDto orderDetailUpdateDto);
    String  deleteOrderDetail(long orderDetailId);

}

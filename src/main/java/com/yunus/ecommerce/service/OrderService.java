package com.yunus.ecommerce.service;

import com.yunus.ecommerce.dto.OrderDtos.OrderDto;
import com.yunus.ecommerce.dto.OrderDtos.OrderUpdateDto;
import com.yunus.ecommerce.entity.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderDto getOrderById(long orderId);
    List<OrderDto> getOrders();
    OrderDto createOrder(Long userId);
    OrderDto updateOrder(OrderUpdateDto orderUpdateDto);
    String  deleteOrder(long orderId);
    OrderDto updateOrderStatus(long orderId, OrderStatus orderStatus);


}

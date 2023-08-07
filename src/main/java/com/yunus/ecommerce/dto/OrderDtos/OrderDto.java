package com.yunus.ecommerce.dto.OrderDtos;

import com.yunus.ecommerce.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private Long orderId;
    private LocalDateTime orderDate;
    private Float orderTotal;
    private OrderStatus orderStatus;
    private Long userId;
    private List<Order_OrderDetailDto> orderDetail;
}

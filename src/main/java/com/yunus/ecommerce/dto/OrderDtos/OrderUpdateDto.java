package com.yunus.ecommerce.dto.OrderDtos;

import com.yunus.ecommerce.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderUpdateDto {
    private Long orderId;
    private Float orderTotal;
    private OrderStatus orderStatus;
    private List<OrderOrderDetailsDto> orderDetails;
}

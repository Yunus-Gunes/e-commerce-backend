package com.yunus.ecommerce.dto.OrderDtos;

import com.yunus.ecommerce.dto.OrderDetailDtos.OrderDetailDto;
import com.yunus.ecommerce.entity.OrderDetail;
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
    private Long customerId;
    private List<OrderOrderDetailsDto> orderDetails;
}

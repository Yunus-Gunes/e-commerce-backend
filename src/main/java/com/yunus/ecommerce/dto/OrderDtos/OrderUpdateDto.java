package com.yunus.ecommerce.dto.OrderDtos;

import com.yunus.ecommerce.entity.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderUpdateDto {
    //Order update olmaz sadece status update olabilir burayı silebiliriz status dışında
    private Long orderId;
    private Float orderTotal;
    private OrderStatus orderStatus;
    private List<Order_OrderDetailDto> baskets;
}

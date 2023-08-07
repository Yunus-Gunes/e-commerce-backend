package com.yunus.ecommerce.dto.UserDtos;

import com.yunus.ecommerce.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserOrdersDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private Float orderTotal;
    private OrderStatus orderStatus;
}

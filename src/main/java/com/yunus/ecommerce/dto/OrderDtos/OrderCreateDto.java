package com.yunus.ecommerce.dto.OrderDtos;

import com.yunus.ecommerce.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderCreateDto {
    private LocalDateTime orderDate;
    private Float orderTotal;
    private Long customerId;
}

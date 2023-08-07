package com.yunus.ecommerce.dto.OrderDtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderCreateDto {
    private LocalDateTime orderDate;
    private Float orderTotal;
    private Long userId;
}

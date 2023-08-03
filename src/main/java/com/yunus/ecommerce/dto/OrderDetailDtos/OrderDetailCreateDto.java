package com.yunus.ecommerce.dto.OrderDetailDtos;

import lombok.Data;

@Data
public class OrderDetailCreateDto {
    private Float productPrice;
    private Float subTotal;
    private Long productId;
    private Long orderId;
}

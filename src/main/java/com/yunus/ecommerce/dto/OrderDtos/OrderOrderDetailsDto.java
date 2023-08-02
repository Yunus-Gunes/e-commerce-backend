package com.yunus.ecommerce.dto.OrderDtos;

import lombok.Data;

@Data
public class OrderOrderDetailsDto {
    private Long orderDetailId;
    private Float productPrice;
    private Float subTotal;
    private Long productId;
}

package com.yunus.ecommerce.dto.OrderDtos;

import lombok.Data;

@Data
public class Order_OrderDetailDto {
    private Long orderDetailId;
    private Float orderDetailProductPrice;
    private Integer numberOfOrderProducts;
    private Long productId;
}

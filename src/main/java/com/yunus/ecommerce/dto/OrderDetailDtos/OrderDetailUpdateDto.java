package com.yunus.ecommerce.dto.OrderDetailDtos;

import lombok.Data;

@Data
public class OrderDetailUpdateDto {
    private Long orderDetailId;
    private Float productPrice;
    private Float subTotal;
}

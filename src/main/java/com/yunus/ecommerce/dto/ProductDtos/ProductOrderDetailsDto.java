package com.yunus.ecommerce.dto.ProductDtos;

import com.yunus.ecommerce.entity.Order;
import lombok.Data;

@Data
public class ProductOrderDetailsDto {
    private Long orderDetailId;
    private Float productPrice;
    private Float subTotal;
    private Order order;
}

package com.yunus.ecommerce.dto.ProductDtos;

import com.yunus.ecommerce.entity.Order;
import lombok.Data;

@Data
public class ProductBasketDto {
    private Long basketId;
    private Float productPrice;
    private Float subTotal;
    private Order order;
}

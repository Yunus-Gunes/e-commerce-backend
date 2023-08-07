package com.yunus.ecommerce.dto.BasketsDtos;

import lombok.Data;

@Data
public class BasketDto {
    private Long basketId;
    private Integer numberOfProducts;
    private Long productId;
    private Long userId;
}

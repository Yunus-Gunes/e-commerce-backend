package com.yunus.ecommerce.dto.BasketsDtos;

import lombok.Data;

@Data
public class BasketCreateDto {
    private Integer numberOfProducts;
    private Long productId;
    private Long userId;
}

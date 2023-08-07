package com.yunus.ecommerce.dto.BasketsDtos;

import lombok.Data;

@Data
public class BasketUpdateDto {
    private Long basketId;
    private Integer numberOfProducts;
}

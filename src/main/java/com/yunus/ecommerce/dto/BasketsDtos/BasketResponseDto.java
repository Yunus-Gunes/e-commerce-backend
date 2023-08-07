package com.yunus.ecommerce.dto.BasketsDtos;

import lombok.Data;

import java.util.List;

@Data
public class BasketResponseDto {
    private Float totalBasketPrice;
    private List<BasketResponseProductsDto> basketProduct;
}

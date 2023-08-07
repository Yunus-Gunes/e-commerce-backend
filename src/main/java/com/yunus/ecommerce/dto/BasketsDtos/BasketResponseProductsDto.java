package com.yunus.ecommerce.dto.BasketsDtos;

import lombok.Data;


@Data
public class BasketResponseProductsDto {
    private Long basketId;
    private Integer numberOfProducts;
    private Long productId;
    private Float totalProductPrice;
}

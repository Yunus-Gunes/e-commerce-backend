package com.yunus.ecommerce.dto.CategoryDtos;

import lombok.Data;

@Data
public class CategoryProductsDto {
    private Long productId;
    private String productName;
    private String productDesc;
    private Float productPrice;
}

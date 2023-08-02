package com.yunus.ecommerce.dto.ProductDtos;

import lombok.Data;

@Data
public class ProductCreateDto {
    private String productName;
    private String productDesc;
    private Float productPrice;
    private Long productCategoryId;
}

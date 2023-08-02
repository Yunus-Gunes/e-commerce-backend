package com.yunus.ecommerce.dto.ProductDtos;

import lombok.Data;

@Data
public class ProductUpdateDto {

    private Long productId;
    private String productName;
    private String productDesc;
    private Float productPrice;
    private Long productCategoryId;
}

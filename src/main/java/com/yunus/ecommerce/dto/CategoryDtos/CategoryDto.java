package com.yunus.ecommerce.dto.CategoryDtos;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private List<CategoryProductsDto> products;
}

package com.yunus.ecommerce.dto.CategoryDtos;

import com.yunus.ecommerce.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryUpdateDto {
    private Long categoryId;
    private String categoryName;
    private List<CategoryProductsDto> products;
}

package com.yunus.ecommerce.service;

import com.yunus.ecommerce.dto.CategoryDtos.CategoryCreateDto;
import com.yunus.ecommerce.dto.CategoryDtos.CategoryDto;
import com.yunus.ecommerce.dto.CategoryDtos.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategoryById(long categoryID);
    List<CategoryDto> getCategories();
    CategoryDto createCategory(CategoryCreateDto categoryCreateDto);
    CategoryDto updateCategory(CategoryUpdateDto categoryUpdateDto);
    String  deleteCategory(Long categoryId);

}

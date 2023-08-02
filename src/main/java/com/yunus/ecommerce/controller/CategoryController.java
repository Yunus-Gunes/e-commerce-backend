package com.yunus.ecommerce.controller;

import com.yunus.ecommerce.dto.CategoryDtos.CategoryCreateDto;
import com.yunus.ecommerce.dto.CategoryDtos.CategoryDto;
import com.yunus.ecommerce.dto.CategoryDtos.CategoryUpdateDto;
import com.yunus.ecommerce.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getCategoryById(@PathVariable long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(){
        return categoryService.getCategories();
    }

    @PostMapping("/create")
    public CategoryDto createAuthor(@RequestBody CategoryCreateDto categoryCreateDto) {
        return categoryService.createCategory(categoryCreateDto);
    }

    @PutMapping("/update")
    public CategoryDto updateAuthor(@RequestBody CategoryUpdateDto categoryUpdateDto) {
        return categoryService.updateCategory(categoryUpdateDto);
    }

    @DeleteMapping("/delete")
    public String  deleteAuthor(@RequestParam long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }


}

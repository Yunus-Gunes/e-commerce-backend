package com.yunus.ecommerce.service.impl;

import com.yunus.ecommerce.dto.CategoryDtos.CategoryCreateDto;
import com.yunus.ecommerce.dto.CategoryDtos.CategoryDto;
import com.yunus.ecommerce.dto.CategoryDtos.CategoryUpdateDto;
import com.yunus.ecommerce.entity.Category;
import com.yunus.ecommerce.repository.CategoryRepository;
import com.yunus.ecommerce.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;

    }

    public CategoryDto getCategoryById(long categoryId){
        String errorMessage = "Category does not exist";
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new DataIntegrityViolationException(errorMessage));

        return this.modelMapper.map(category,CategoryDto.class) ;
    }

    public List<CategoryDto> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());
    }

    public CategoryDto createCategory(CategoryCreateDto categoryCreateDto){
        Category category = new Category();
        category.setCategoryName(categoryCreateDto.getCategoryName());

        try{
            categoryRepository.save(category);
            return this.modelMapper.map(category,CategoryDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Failed to create category.";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public CategoryDto updateCategory(CategoryUpdateDto categoryUpdateDto){
        CategoryDto categoryDto = getCategoryById(categoryUpdateDto.getCategoryId());
        Category category = this.modelMapper.map(categoryDto, Category.class);

        try {
            category.setCategoryName(categoryUpdateDto.getCategoryName());

            categoryRepository.save(category);
            return this.modelMapper.map(category,CategoryDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Category failed to update";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public String  deleteCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if(category.isPresent() ){
            categoryRepository.deleteById(categoryId);
            return "The category deleted.";
        }else {
            return "Category not found.";
        }
    }


}

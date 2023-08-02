package com.yunus.ecommerce.service;

import com.yunus.ecommerce.dto.ProductDtos.ProductCreateDto;
import com.yunus.ecommerce.dto.ProductDtos.ProductDto;
import com.yunus.ecommerce.dto.ProductDtos.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(long productId);
    List<ProductDto> getProducts();
    ProductDto createProduct(ProductCreateDto productCreateDto);
    ProductDto updateProduct(ProductUpdateDto productUpdateDto);
    String  deleteProduct(Long productId);

}

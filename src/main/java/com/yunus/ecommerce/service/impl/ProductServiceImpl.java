package com.yunus.ecommerce.service.impl;


import com.yunus.ecommerce.dto.ProductDtos.ProductCreateDto;
import com.yunus.ecommerce.dto.ProductDtos.ProductDto;
import com.yunus.ecommerce.dto.ProductDtos.ProductUpdateDto;
import com.yunus.ecommerce.entity.Category;
import com.yunus.ecommerce.entity.Product;
import com.yunus.ecommerce.repository.CategoryRepository;
import com.yunus.ecommerce.repository.ProductRepository;
import com.yunus.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;

    }

    public ProductDto getProductById(long productId){
        String errorMessage = "Product does not exist";
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new DataIntegrityViolationException(errorMessage));

        return this.modelMapper.map(product,ProductDto.class) ;
    }

    public List<ProductDto> getProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product,ProductDto.class))
                .collect(Collectors.toList());
    }

    
    public ProductDto createProduct(ProductCreateDto productCreateDto){
        Product product = new Product();
        product.setProductName(productCreateDto.getProductName());
        product.setProductDesc(productCreateDto.getProductDesc());
        Category category = categoryRepository.findById(productCreateDto.getProductCategoryId()).orElse(null);
        product.setProductCategory(category);
        product.setProductPrice(productCreateDto.getProductPrice());


        try{
            productRepository.save(product);
            return this.modelMapper.map(product,ProductDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Failed to create product.";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public ProductDto updateProduct(ProductUpdateDto productUpdateDto){
        ProductDto productDto = getProductById(productUpdateDto.getProductId());
        Product product = this.modelMapper.map(productDto, Product.class);

        try {
            product.setProductName(productUpdateDto.getProductName());
            product.setProductPrice(productUpdateDto.getProductPrice());
            product.setProductDesc(productUpdateDto.getProductDesc());
            Category category = categoryRepository.findById(productUpdateDto.getProductCategoryId()).get();
            product.setProductCategory(category);

            productRepository.save(product);

            return this.modelMapper.map(product,ProductDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Product failed to update";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public String  deleteProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if(product.isPresent() ){
            productRepository.deleteById(productId);
            return "The product deleted.";
        }else {
            return "Product not found.";
        }
    }

    


}

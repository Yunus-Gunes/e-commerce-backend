package com.yunus.ecommerce.controller;

import com.yunus.ecommerce.dto.ProductDtos.ProductCreateDto;
import com.yunus.ecommerce.dto.ProductDtos.ProductDto;
import com.yunus.ecommerce.dto.ProductDtos.ProductUpdateDto;
import com.yunus.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/byCategory/{categoryId}")
    public List<ProductDto> getProductsByCategory(@PathVariable long categoryId){
        return productService.getProductsByCategory(categoryId);
    }

    @PostMapping("/create")
    public ProductDto createProduct(@RequestBody ProductCreateDto productCreateDto) {
        return productService.createProduct(productCreateDto);
    }

    @PutMapping("/update")
    public ProductDto updateProduct(@RequestBody ProductUpdateDto productUpdateDto) {
        return productService.updateProduct(productUpdateDto);
    }

    @DeleteMapping("/delete")
    public String  deleteProduct(@RequestParam long productId) {
        return productService.deleteProduct(productId);
    }








}


package com.yunus.ecommerce.service.impl;


import com.yunus.ecommerce.dto.BasketsDtos.*;
import com.yunus.ecommerce.entity.User;
import com.yunus.ecommerce.entity.Basket;
import com.yunus.ecommerce.entity.Product;
import com.yunus.ecommerce.repository.*;
import com.yunus.ecommerce.service.BasketService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public BasketServiceImpl(BasketRepository basketRepository, OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.basketRepository = basketRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public BasketDto getBasketById(long basketId){
        String errorMessage = "Basket does not exist";
        Basket basket = this.basketRepository.findById(basketId)
                .orElseThrow(() -> new DataIntegrityViolationException(errorMessage));

        return this.modelMapper.map(basket,BasketDto.class);
        //return basketMapper.basketToBasketDto(basket);
    }

    public List<BasketDto> getBaskets(){
        List<Basket> baskets = basketRepository.findAll();
        return baskets.stream()
                .map(basket -> modelMapper.map(basket,BasketDto.class))
                .collect(Collectors.toList());
    }
    
    public BasketDto createBasket(BasketCreateDto basketCreateDto){

        Basket basket = new Basket();

        Product product = productRepository.getReferenceById(basketCreateDto.getProductId());
        basket.setBasketProduct(product);

        User user = userRepository.getReferenceById(basketCreateDto.getUserId());
        basket.setUserBasket(user);

        basket.setNumberOfProducts(basketCreateDto.getNumberOfProducts());

        try{
            basketRepository.save(basket);
            return this.modelMapper.map(basket,BasketDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Failed to create basket.";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public BasketDto updateBasket(BasketUpdateDto basketUpdateDto){
        BasketDto basketDto = getBasketById(basketUpdateDto.getBasketId());
        Basket basket = this.modelMapper.map(basketDto, Basket.class);

        try {
            basket.setNumberOfProducts(basketUpdateDto.getNumberOfProducts());

            basketRepository.save(basket);

            return this.modelMapper.map(basket,BasketDto.class);
        } catch (DataIntegrityViolationException ex){
            String errorMessage = "Basket failed to update";
            throw new DataIntegrityViolationException(errorMessage);
        }
    }

    public String  deleteBasket(long basketId) {
        Optional<Basket> basket = basketRepository.findById(basketId);

        if(basket.isPresent() ){
            basketRepository.deleteById(basketId);
            return "The basket deleted.";
        }else {
            return "Basket not found.";
        }
    }

    public BasketResponseDto getUserBasketByUserId(long userId){
        BasketResponseDto basketResponseDto = new BasketResponseDto();

        List<Basket> basketProduct = basketRepository.findByUserId(userId);

        List<BasketResponseProductsDto> basketResponseProductsDtoList = new ArrayList<>();
        float orderTotalPrice = 0.0F;
        for (Basket basketElemenet: basketProduct) {
            BasketResponseProductsDto basketResponseProductsDto = new BasketResponseProductsDto();

            basketResponseProductsDto.setProductId(basketElemenet.getBasketProduct().getProductId());
            basketResponseProductsDto.setBasketId(basketElemenet.getBasketId());
            basketResponseProductsDto.setNumberOfProducts(basketElemenet.getNumberOfProducts());
            basketResponseProductsDto.setBasketId(basketElemenet.getBasketId());

            Float basketOrderPrice = Float.valueOf(productRepository.findProductPrice(basketElemenet.getBasketProduct().getProductId()));
            basketResponseProductsDto.setTotalProductPrice(basketOrderPrice*basketElemenet.getNumberOfProducts());

            orderTotalPrice += basketOrderPrice*basketElemenet.getNumberOfProducts();

            basketResponseProductsDtoList.add(basketResponseProductsDto);
        }
        basketResponseDto.setBasketProduct(basketResponseProductsDtoList);
        basketResponseDto.setTotalBasketPrice(orderTotalPrice);

        return basketResponseDto;
        //return basketMapper.basketToBasketDto(basket);
    }

    


}

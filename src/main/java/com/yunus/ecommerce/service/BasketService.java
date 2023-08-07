package com.yunus.ecommerce.service;

import com.yunus.ecommerce.dto.BasketsDtos.BasketCreateDto;
import com.yunus.ecommerce.dto.BasketsDtos.BasketDto;
import com.yunus.ecommerce.dto.BasketsDtos.BasketResponseDto;
import com.yunus.ecommerce.dto.BasketsDtos.BasketUpdateDto;

import java.util.List;

public interface BasketService {
    BasketDto getBasketById(long basketId);
    List<BasketDto> getBaskets();
    BasketDto createBasket(BasketCreateDto basketCreateDto);
    BasketDto updateBasket(BasketUpdateDto basketUpdateDto);
    String  deleteBasket(long basketId);
    BasketResponseDto getUserBasketByUserId(long userId);


}

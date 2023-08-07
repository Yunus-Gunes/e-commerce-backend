package com.yunus.ecommerce.controller;

import com.yunus.ecommerce.dto.BasketsDtos.BasketCreateDto;
import com.yunus.ecommerce.dto.BasketsDtos.BasketDto;
import com.yunus.ecommerce.dto.BasketsDtos.BasketResponseDto;
import com.yunus.ecommerce.dto.BasketsDtos.BasketUpdateDto;
import com.yunus.ecommerce.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/{basketId}")
    public BasketDto getBasketById(@PathVariable long basketId) {
        return basketService.getBasketById(basketId);
    }

    @GetMapping("/baskets")
    public List<BasketDto> getBaskets(){
        return basketService.getBaskets();
    }

    @GetMapping("/user-baskets")
    public BasketResponseDto getUserBasketByUserId(long userId){
        return basketService.getUserBasketByUserId(userId);
    }


    @PostMapping("/create")
    public BasketDto createBasket(@RequestBody BasketCreateDto basketCreateDto) {
        return basketService.createBasket(basketCreateDto);
    }

    @PutMapping("/update")
    public BasketDto updateBasket(@RequestBody BasketUpdateDto basketUpdateDto) {
        return basketService.updateBasket(basketUpdateDto);
    }

    @DeleteMapping("/delete")
    public String  deleteBasket(@RequestParam long basketId) {
        return basketService.deleteBasket(basketId);
    }

}

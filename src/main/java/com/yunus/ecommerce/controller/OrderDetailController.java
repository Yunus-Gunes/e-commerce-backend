package com.yunus.ecommerce.controller;

import com.yunus.ecommerce.dto.OrderDetailDtos.OrderDetailCreateDto;
import com.yunus.ecommerce.dto.OrderDetailDtos.OrderDetailDto;
import com.yunus.ecommerce.dto.OrderDetailDtos.OrderDetailUpdateDto;
import com.yunus.ecommerce.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orderDetail")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

//    public OrderDetailController(OrderDetailService orderDetailService) {
//        this.orderDetailService = orderDetailService;
//    }

    @GetMapping("/{orderDetailId}")
    public OrderDetailDto getOrderDetailById(@PathVariable long orderDetailId) {
        return orderDetailService.getOrderDetailById(orderDetailId);
    }

    @GetMapping("/orderDetails")
    public List<OrderDetailDto> getOrderDetails(){
        return orderDetailService.getOrderDetails();
    }

    @PostMapping("/create")
    public OrderDetailDto createAuthor(@RequestBody OrderDetailCreateDto orderDetailCreateDto) {
        return orderDetailService.createOrderDetail(orderDetailCreateDto);
    }

    @PutMapping("/update")
    public OrderDetailDto updateAuthor(@RequestBody OrderDetailUpdateDto orderDetailUpdateDto) {
        return orderDetailService.updateOrderDetail(orderDetailUpdateDto);
    }

    @DeleteMapping("/delete")
    public String  deleteAuthor(@RequestParam long orderDetailId) {
        return orderDetailService.deleteOrderDetail(orderDetailId);
    }


}

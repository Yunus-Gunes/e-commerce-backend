package com.yunus.ecommerce.controller;

import com.yunus.ecommerce.dto.OrderDtos.OrderDto;
import com.yunus.ecommerce.dto.OrderDtos.OrderUpdateDto;
import com.yunus.ecommerce.entity.OrderStatus;
import com.yunus.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping("/create")
    public OrderDto createOrder(@RequestParam long userId) {
        return orderService.createOrder(userId);
    }

    @PutMapping("/update")
    public OrderDto updateOrder(@RequestBody OrderUpdateDto orderUpdateDto) {
        return orderService.updateOrder(orderUpdateDto);
    }

    @DeleteMapping("/delete")
    public String  deleteOrder(@RequestParam long orderId) {
        return orderService.deleteOrder(orderId);
    }

    @PutMapping("/updatestatus")
    public OrderDto updateOrderStatus(@RequestBody long orderId, OrderStatus orderStatus) {
        return orderService.updateOrderStatus(orderId,  orderStatus);
    }
}

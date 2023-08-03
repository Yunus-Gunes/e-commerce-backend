package com.yunus.ecommerce.mapper;

import com.yunus.ecommerce.dto.OrderDetailDtos.OrderDetailDto;
import com.yunus.ecommerce.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderDetailMapper {

    /*
    @Mapping(target = "orderDetailId",source = "orderDetail.orderDetailId")
    @Mapping(target = "productPrice",source = "orderDetail.productPrice")
    @Mapping(target = "subTotal",source = "orderDetail.subTotal")
    @Mapping(target = "productId",source = "orderDetail.product")
    @Mapping(target = "orderId",source = "orderDetail.order")

    OrderDetailDto orderDetailToOrderDetailDto(OrderDetail orderDetail);

     */
}

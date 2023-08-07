package com.yunus.ecommerce.dto.UserDtos;

import lombok.Data;

@Data
public class UserBasketsDto {
    private Long basketId;
    private Integer numberOfProducts;
    private Long basketProductId;
}

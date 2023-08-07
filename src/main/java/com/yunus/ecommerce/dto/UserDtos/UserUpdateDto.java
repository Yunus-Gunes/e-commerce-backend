package com.yunus.ecommerce.dto.UserDtos;

import lombok.Data;

import java.util.List;

@Data
public class UserUpdateDto {

    private Long userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userAddress;
    private List<UserOrdersDto> orders;
}

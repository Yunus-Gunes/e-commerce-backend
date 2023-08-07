package com.yunus.ecommerce.dto.UserDtos;

import lombok.Data;

@Data
public class UserCreateDto {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userAddress;
}

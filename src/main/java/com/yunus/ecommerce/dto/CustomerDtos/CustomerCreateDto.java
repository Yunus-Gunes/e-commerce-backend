package com.yunus.ecommerce.dto.CustomerDtos;

import lombok.Data;

import java.util.List;

@Data
public class CustomerCreateDto {
    private String customerName;
    private String customerEmail;
    private String customerPassword;
    private String customerAddress;
}

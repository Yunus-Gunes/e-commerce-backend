package com.yunus.ecommerce.dto.CustomerDtos;

import com.yunus.ecommerce.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {

    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String customerPassword;
    private String customerAddress;
    private List<CustomerOrdersDto> orders;
}
